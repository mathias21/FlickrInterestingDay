package com.batcuevasoft.flickrinterestingday.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class BaseRepositoryTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

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