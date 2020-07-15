package com.batcuevasoft.flickrinterestingday.data.injection

import android.app.Application
import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepositoryImp
import org.koin.dsl.module

fun Application.dataModules() = module {
    factory<FlickrPictureRepository> { FlickrPictureRepositoryImp() }
}