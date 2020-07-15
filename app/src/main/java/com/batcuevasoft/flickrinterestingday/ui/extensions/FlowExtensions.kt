package com.batcuevasoft.flickrinterestingday.ui.extensions

import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.asLiveData() = liveData {
    collect {
        emit(it)
    }
}

fun <T> Flow<T>.asPagedLiveData() = liveData {
    collect {
        emit(it)
    }
}