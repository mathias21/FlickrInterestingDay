package com.batcuevasoft.flickrinterestingday.data.pandas

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.Panda

interface FlickrPandaRepository {
    fun getPandas(): List<Panda>
}