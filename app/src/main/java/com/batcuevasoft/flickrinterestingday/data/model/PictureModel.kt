package com.batcuevasoft.flickrinterestingday.data.model

import com.batcuevasoft.flickrinterestingday.ui.adapter.StableIdElement
import java.io.Serializable

data class FlickrPicture(
    val id: Long,
    val name: String,
    val pictureUrl: String,
    val isPublic: Boolean
) : StableIdElement, Serializable {
    override fun getReusableId() = id
}