package com.batcuevasoft.flickrinterestingday.datasource.remote.model

import androidx.annotation.Keep
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.google.gson.annotations.SerializedName

@Keep
data class PandaWrapperRemoteEntity(
    val pandas: List<Panda>,
    val stat: String
)

@Keep
data class Panda(
    @SerializedName("_content")
    val pandaName: String
)

data class GetPandasParams(
    val apiKey: String = "51f62ed1594b40bb572d2225098175f8",
    val contentType: String = "1",
    val media: String = "photos",
    val format: String = "json",
    val nojsoncallback: String = "1"
)

fun GetPandasParams.toQueryMap() = mapOf(
    "api_key" to apiKey,
    "content_type" to contentType,
    "media" to media,
    "format" to format,
    "nojsoncallback" to nojsoncallback
)