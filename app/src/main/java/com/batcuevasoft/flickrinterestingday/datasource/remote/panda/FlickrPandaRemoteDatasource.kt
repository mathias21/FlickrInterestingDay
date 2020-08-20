package com.batcuevasoft.flickrinterestingday.datasource.remote.panda

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.*
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrNullBodyException
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrOtherException


class FlickrPandaRemoteDatasourceImp(
    private val flickrPandaService: FlickrPandaService
) : FlickrPandaRemoteDatasource {

    override fun getPandas(): PandaWrapperRemoteEntity {
        val pandaResponse = flickrPandaService.getPandas(
            GetPandasParams().toQueryMap()
        ).execute()
        if (pandaResponse.isSuccessful) {
            pandaResponse.body()?.let {
                return it
            } ?: throw FlickrNullBodyException()
        } else {
            // Manage here other possible scenarios, with proper reaction to them
            throw FlickrOtherException()
        }
    }
}

interface FlickrPandaRemoteDatasource {
    fun getPandas(): PandaWrapperRemoteEntity
}