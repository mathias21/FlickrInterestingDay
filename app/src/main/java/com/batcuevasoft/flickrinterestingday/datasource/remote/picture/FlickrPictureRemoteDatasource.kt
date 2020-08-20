package com.batcuevasoft.flickrinterestingday.datasource.remote.picture

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.*


class FlickrPictureRemoteDatasourceImp(
    private val flickrPictureService: FlickrPictureService
) : FlickrPictureRemoteDatasource {

    override fun getPicturesFromDate(dateString: String, page: Int): PictureWrapperRemoteEntity {
        val trackPictureResponse = flickrPictureService.getInterstingPictures(
            GetInterestingPicturesParams(date = dateString, page = page).toQueryMap()
        ).execute()
        if (trackPictureResponse.isSuccessful) {
            trackPictureResponse.body()?.let {
                return it
            } ?: throw FlickrNullBodyException()
        } else {
            // Manage here other possible scenarios, with proper reaction to them
            throw FlickrOtherException()
        }
    }

    override fun getPlacePicturesFromTag(tag: String): PictureWrapperRemoteEntity {
        flickrPictureService.getPlacePicturesByTag(

        )
    }
}

interface FlickrPictureRemoteDatasource {
    fun getPicturesFromDate(dateString: String, page: Int): PictureWrapperRemoteEntity
    fun getPlacePicturesFromTag(tag: String): PictureWrapperRemoteEntity
}