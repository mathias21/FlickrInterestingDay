package com.batcuevasoft.flickrinterestingday.domain

interface InvokerInt {

    var operationListenerList: MutableList<AsyncListener>

    fun <P, T> execute(useCase: BaseUseCaseInt<P, T>, reporter: (ResultReporterImp<T>.() -> Unit))

    fun cancelTasks()


    // TO NOTIFY ASYNC OPERATIONS STATUS, EXTRA
    fun registerListener(asyncListener: AsyncListener) {
        operationListenerList.add(asyncListener)
    }

    fun unregisterListener(asyncListener: AsyncListener) {
        operationListenerList.remove(asyncListener)
    }
}

interface AsyncListener {
    fun operationStarted()
    fun operationEnded()
}