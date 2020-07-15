package com.batcuevasoft.flickrinterestingday.data.injection

import android.app.Application
import com.batcuevasoft.flickrinterestingday.data.picturerepository.PictureRepository
import com.batcuevasoft.flickrinterestingday.data.picturerepository.PictureRepositoryImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.ServiceBuilder
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasource
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasourceImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureService
import org.koin.dsl.module

fun Application.dataModules() = module {
    factory<PictureRepository> { PictureRepositoryImp() }
}