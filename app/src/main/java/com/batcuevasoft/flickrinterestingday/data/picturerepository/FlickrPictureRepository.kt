package com.batcuevasoft.flickrinterestingday.data.picturerepository

import androidx.paging.PagingData
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import kotlinx.coroutines.flow.Flow

interface FlickrPictureRepository {
    fun getYesterdayPictures(): Flow<PagingData<FlickrPicture>>
}