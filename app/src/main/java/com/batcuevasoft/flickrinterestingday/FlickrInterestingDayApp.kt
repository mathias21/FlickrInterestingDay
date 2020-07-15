package com.batcuevasoft.flickrinterestingday

import android.app.Application
import com.batcuevasoft.flickrinterestingday.data.injection.dataModules
import com.batcuevasoft.flickrinterestingday.datasource.remote.injection.remoteModules
import com.batcuevasoft.flickrinterestingday.ui.injection.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlickrInterestingDayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    uiModules(),
                    remoteModules(),
                    dataModules()
                )
            )
            androidContext(this@FlickrInterestingDayApp)
        }
    }
}