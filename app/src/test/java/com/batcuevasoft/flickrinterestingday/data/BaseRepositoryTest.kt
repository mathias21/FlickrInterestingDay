package com.batcuevasoft.flickrinterestingday.data

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class BaseRepositoryTest {

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