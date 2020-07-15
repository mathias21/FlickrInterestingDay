package com.batcuevasoft.flickrinterestingday.datasource.remote.picture

import androidx.annotation.Keep
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PictureWrapperRemoteEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

@Keep
interface FlickrPictureService {

    @GET("?method=flickr.interestingness.getList")
    fun getTrackPicture(@QueryMap params: Map<String, String>): Call<PictureWrapperRemoteEntity>
}
