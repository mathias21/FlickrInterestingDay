package com.batcuevasoft.flickrinterestingday.domain

import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UseCaseInvoker(
    override var operationListenerList: MutableList<AsyncListener> = mutableListOf()
) : CoroutineScope, InvokerInt {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun <P, T> execute(useCase: BaseUseCaseInt<P, T>, reporter: (ResultReporterImp<T>.() -> Unit)) {
        try {
            launch(useCase.foregroundDispatcher + CoroutineExceptionHandler { _, e ->
                Log.e("TAG", "CoroutineExceptionHandler", e)
            }) {
                // We notify all listeners we are going to start
                operationListenerList.forEach { it.operationStarted() }

                // With background dispatcher (not necesarily background thread) we invoke async call
                val deferred = async(useCase.backgroundDispatcher) {
                    useCase.resultReporter.reporter()
                    useCase.doTheJob()
                }

                // With foreground dispatcher (not necesarily main thread) we notify operation result
                withContext(useCase.foregroundDispatcher) {
                    val result = deferred.await()
                    useCase.resultReporter.beforeResult()
                    operationListenerList.forEach { it.operationEnded() }
                    when (result) {
                        is Success -> useCase.resultReporter.onSuccess(result.data)
                        else -> useCase.resultReporter.onError(result)
                    }
                    useCase.resultReporter.finally()
                    useCase.resultReporter.reset()
                }
            }
        } catch (e: Exception) {
            print(e.localizedMessage)
            useCase.resultReporter.onError(UnknownError())
            useCase.resultReporter.reset()
        }
    }

    override fun cancelTasks() {
        job.cancel()
    }
}