package com.batcuevasoft.flickrinterestingday.ui.main.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrNullBodyException
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrOtherException
import com.batcuevasoft.flickrinterestingday.ui.main.today.TodayViewEvent.*
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.BaseViewModel
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodayViewModel(
    private val pictureRepository: FlickrPictureRepository
) : BaseViewModel() {

    private val _todayEvents = MutableLiveData<Event<TodayViewEvent>>()
    val todayEvents: LiveData<Event<TodayViewEvent>>
        get() = _todayEvents

    private val _todayPictures = MutableLiveData<PagingData<FlickrPicture>>()
    val todayPictures: LiveData<PagingData<FlickrPicture>>
        get() = _todayPictures

    init {
        refreshPictures()
    }

    private suspend fun handleGetPicturesError(it: Throwable) {
        emitEvent(HIDE_LOADING)
        when (it) {
            is FlickrNullBodyException -> emitEvent(GET_PICTURES_ERROR)
            is FlickrOtherException -> emitEvent(DEFAULT_ERROR)
        }
    }

    private suspend fun emitEvent(event: TodayViewEvent) {
        withContext(coroutineDispatcherProvider.main) {
            _todayEvents.value = Event(event)
        }
    }

    @ExperimentalCoroutinesApi
    fun refreshPictures() {
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            pictureRepository.getYesterdayPictures()
                .onStart { emitEvent(SHOW_LOADING) }
                .catch { handleGetPicturesError(it) }
                .cachedIn(this)
                .onCompletion { emitEvent(HIDE_LOADING) }
                .collect {
                    _todayPictures.postValue(it)
                }
        }
    }
}

enum class TodayViewEvent {
    SHOW_LOADING,
    HIDE_LOADING,
    DEFAULT_ERROR,
    GET_PICTURES_ERROR
}