package com.batcuevasoft.flickrinterestingday.ui.viewmodelutils

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val coroutineDispatcherProvider: CoroutineDispatcherProvider by inject()
}