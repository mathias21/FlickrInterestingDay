package com.batcuevasoft.flickrinterestingday.datasource.remote.panda

import androidx.annotation.Keep
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PandaWrapperRemoteEntity
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PictureWrapperRemoteEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

@Keep
interface FlickrPandaService {

    @GET("?method=flickr.panda.getList")
    fun getPandas(@QueryMap params: Map<String, String>): Call<PandaWrapperRemoteEntity>
}
