package com.batcuevasoft.flickrinterestingday.ui.main.past

import androidx.lifecycle.ViewModel
import com.batcuevasoft.flickrinterestingday.domain.past.GetPandaPicturesUseCase
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.BaseViewModel

class PastViewModel(
    private val getPandaPicturesUseCase: GetPandaPicturesUseCase
) : BaseViewModel() {

    fun onStart() {
        invoker.execute(getPandaPicturesUseCase) {
            onSuccess {

            }
            onError {

            }
        }
    }

}