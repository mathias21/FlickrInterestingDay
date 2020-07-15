package com.batcuevasoft.flickrinterestingday.datasource.remote.injection

import android.app.Application
import com.batcuevasoft.flickrinterestingday.datasource.remote.ServiceBuilder
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasource
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasourceImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureService
import com.batcuevasoft.flickrinterestingday.ui.main.today.TodayViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun Application.remoteModules() = module {
    single { ServiceBuilder() }
    factory<FlickrPictureRemoteDatasource> { FlickrPictureRemoteDatasourceImp(get()) }
    single { (get() as ServiceBuilder).createService(FlickrPictureService::class.java) }
}