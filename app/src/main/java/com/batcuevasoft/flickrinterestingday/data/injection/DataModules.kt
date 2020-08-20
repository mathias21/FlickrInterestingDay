package com.batcuevasoft.flickrinterestingday.data.injection

import android.app.Application
import com.batcuevasoft.flickrinterestingday.data.pictures.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.data.pictures.FlickrPictureRepositoryImp
import org.koin.dsl.module

fun Application.dataModules() = module {
    factory<FlickrPictureRepository> { FlickrPictureRepositoryImp(get()) }
}