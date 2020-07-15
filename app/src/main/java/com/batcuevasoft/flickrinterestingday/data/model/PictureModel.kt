package com.batcuevasoft.flickrinterestingday.data.model

import java.io.Serializable

data class FlickrPicture(
    val id: Long,
    val name: String,
    val pictureUrl: String,
    val isPublic: Boolean
) : Serializable