package com.batcuevasoft.flickrinterestingday.data.pictures

import androidx.paging.PagingData
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import kotlinx.coroutines.flow.Flow

interface FlickrPictureRepository {
    fun getYesterdayPictures(): Flow<PagingData<FlickrPicture>>
    fun getPicturesFromTag(tag: String): List<FlickrPicture>
}