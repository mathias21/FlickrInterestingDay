package com.batcuevasoft.flickrinterestingday.domain.past

import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.data.pictures.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.domain.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetPandaPicturesUseCase(
    private val picturesRepository: FlickrPictureRepository,
    override val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO,
    override val foregroundDispatcher: CoroutineDispatcher = Dispatchers.Main,
    override var params: String? = null
) : BaseUseCaseInt<String, List<FlickrPicture>>, GetPastPicturesUseCaseInt {

    override var resultReporter: ResultReporterImp<List<FlickrPicture>> = ResultReporterImp()

    override suspend fun doTheJob(): Result<List<FlickrPicture>> {
        params?.let {
            picturesRepository.getPicturesFromTag().flatMap {
                picturesRepository.getPicturesFromTag(it.pandaName)
            }
            val data = picturesRepository.getPicturesFromTag()

            return if (data.isEmpty()) {
                Error("Empty data")
            } else {
                Success(data)
            }
        } ?: throw IllegalArgumentException("Param cannot be null")
    }
}

interface GetPastPicturesUseCaseInt : BaseUseCaseInt<String, List<FlickrPicture>> {
    override suspend fun doTheJob(): Result<List<FlickrPicture>>
}