package com.batcuevasoft.flickrinterestingday.datasource.remote.picture

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.toFlickrPicture

class FlickrPicturePagingSource(
    private val date: String,
    private val flickrPictureRemoteDatasource: FlickrPictureRemoteDatasource
) : PagingSource<Int, FlickrPicture>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrPicture> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = flickrPictureRemoteDatasource.getPicturesFromDate(date, nextPageNumber)
            val nextKey = response.photos.page + 1

            Page(
                data = response.photos.photo.map { it.toFlickrPicture() },
                prevKey = null,
                nextKey = if (nextKey > response.photos.pages) null else nextKey
            )
        } catch (e: Exception) {
            Error(e)
        }
    }
}