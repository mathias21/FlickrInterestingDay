package com.batcuevasoft.flickrinterestingday.data.instrumentation

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.Photo
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.Photos
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PictureWrapperRemoteEntity

object FlickrPictureRepositoryInstrumentation {

    fun getPictureWrapperRemoteEntity() = PictureWrapperRemoteEntity(
        getPhotos(),
        "stat"
    )

    private fun getPhotos() = Photos(
        page = 1,
        pages = 2,
        perpage = 2,
        photo = getPhotoList(),
        total = 4
    )

    private fun getPhotoList() = listOf(
        getPhoto("1", "title1"),
        getPhoto("2", "title2"),
        getPhoto("3", "title3")
    )

    private fun getPhoto(id: String, title: String) = Photo(
        farm = 1,
        id = id,
        isfamily = 0,
        isfriend = 0,
        ispublic = 1,
        owner = "owner",
        secret = "82h23i23e2",
        server = 1,
        title = title
    )
}