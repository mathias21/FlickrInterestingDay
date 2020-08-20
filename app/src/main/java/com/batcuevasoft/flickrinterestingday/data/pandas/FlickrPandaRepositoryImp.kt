package com.batcuevasoft.flickrinterestingday.data.pandas

import com.batcuevasoft.flickrinterestingday.datasource.remote.model.Panda
import com.batcuevasoft.flickrinterestingday.datasource.remote.panda.FlickrPandaRemoteDatasource
import org.koin.core.KoinComponent

class FlickrPandaRepositoryImp(
    private val flickrPandaRemoteDatasource: FlickrPandaRemoteDatasource
) : FlickrPandaRepository, KoinComponent {

    override fun getPandas(): List<Panda> {
        return flickrPandaRemoteDatasource.getPandas().pandas
    }
}