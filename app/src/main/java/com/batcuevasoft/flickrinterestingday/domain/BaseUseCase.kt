package com.batcuevasoft.flickrinterestingday.domain

import kotlinx.coroutines.*

class ResultReporterImp<T> : ResultReporter<T> {

    private var _before: (() -> Unit)? = null
    private var _onSuccess: ((data: T) -> Unit)? = null
    private var _onError: ((errorResult: Result<T>) -> Unit)? = null
    private var _finally: (() -> Unit)? = null

    override fun beforeResult() {
        _before?.invoke()
    }

    fun beforeResult(beforeBlock: () -> Unit) {
        _before = beforeBlock
    }

    override fun onSuccess(data: T) {
        _onSuccess?.invoke(data)
    }

    fun onSuccess(successBlock: (T) -> Unit) {
        _onSuccess = successBlock
    }

    override fun onError(onError: Result<T>) {
        _onError?.invoke(onError)
    }

    fun onError(errorBlock: (Result<T>) -> Unit) {
        _onError = errorBlock
    }

    override fun finally() {
        _finally?.invoke()
    }

    fun finally(finallyBlock: () -> Unit) {
        _finally = finallyBlock
    }

    override fun reset() {
        _before = null
        _onSuccess = null
        _onError = null
        _finally = null
    }
}

interface ResultReporter<T> {
    fun beforeResult()
    fun onSuccess(data: T)
    fun onError(onError: Result<T>)
    fun finally()
    fun reset()
}

interface BaseUseCaseInt<P, T> {
    val backgroundDispatcher: CoroutineDispatcher
    val foregroundDispatcher: CoroutineDispatcher
    var params: P?
    var resultReporter: ResultReporterImp<T>

    suspend fun doTheJob(): Result<T>

    fun withParams(useCaseParams: P) = apply {
        this.params = useCaseParams
    }
}



