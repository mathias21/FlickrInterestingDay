package com.batcuevasoft.flickrinterestingday.ui.injection

import android.app.Application
import com.batcuevasoft.flickrinterestingday.ui.main.today.TodayViewModel
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.CoroutineDispatcherProvider
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.DispatchersProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun Application.uiModules() = module {
    viewModel { TodayViewModel(get()) }
    single<CoroutineDispatcherProvider> { DispatchersProvider() }
}