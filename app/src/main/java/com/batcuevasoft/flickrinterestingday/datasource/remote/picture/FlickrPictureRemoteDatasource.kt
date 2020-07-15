package com.batcuevasoft.flickrinterestingday.datasource.remote.picture

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.GetTrackPictureParams
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PictureWrapperRemoteEntity
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.toQueryMap


class FlickrPictureRemoteDatasourceImp(
    private val flickrPictureService: FlickrPictureService
) : FlickrPictureRemoteDatasource {

    override fun getPicturesFromDate(dateString: String, page: Int): PictureWrapperRemoteEntity {
        val trackPictureResponse = flickrPictureService.getTrackPicture(
            GetTrackPictureParams(date = dateString, page = page).toQueryMap()
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
}

interface FlickrPictureRemoteDatasource {
    fun getPicturesFromDate(dateString: String, page: Int): PictureWrapperRemoteEntity
}