package com.batcuevasoft.flickrinterestingday.datasource.remote.model

import androidx.annotation.Keep
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture

@Keep
data class PictureWrapperRemoteEntity(
    val photos: Photos,
    val stat: String
)

@Keep
data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)

@Keep
data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: Int,
    val title: String
)

//api key, accuracy(16), content_type, media(photos), geo_context(2), lat, long, radius(1)
data class GetTrackPictureParams(
    val date: String,
    val apiKey: String = "51f62ed1594b40bb572d2225098175f8",
    val contentType: String = "1",
    val media: String = "photos",
    val format: String = "json",
    val nojsoncallback: String = "1",
    val perpage: Int = 10,
    val page: Int
)

fun GetTrackPictureParams.toQueryMap() = mapOf(
    "date" to date,
    "api_key" to apiKey,
    "content_type" to contentType,
    "media" to media,
    "format" to format,
    "nojsoncallback" to nojsoncallback,
    "per_page" to perpage.toString(),
    "page" to page.toString()
)

fun Photo.toFlickrPicture() = FlickrPicture(
    id.toLong(),
    if(title.isEmpty()) "No name $id" else title,
    "https://farm${farm}.staticflickr.com/${server}/${id}_${secret}.jpg",
    ispublic == 1
)