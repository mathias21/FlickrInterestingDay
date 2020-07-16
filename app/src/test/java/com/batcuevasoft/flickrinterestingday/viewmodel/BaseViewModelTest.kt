package com.batcuevasoft.flickrinterestingday.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module


abstract class BaseViewModelTest {

    // Needed to avoid the mainLooper error
    @get:Rule
    val rule = InstantTaskExecutorRule()

    init {
        stopKoin()
    }

    fun startInjection(module: () -> Module) {
        startKoin {
            modules(
                module()
            )
        }
    }
}