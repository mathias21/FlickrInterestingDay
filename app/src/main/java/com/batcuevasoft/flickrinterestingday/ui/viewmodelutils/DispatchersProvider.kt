package com.batcuevasoft.flickrinterestingday.ui.viewmodelutils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}