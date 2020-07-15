package com.batcuevasoft.flickrinterestingday.data.picturerepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPicturePagingSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.get
import java.text.SimpleDateFormat
import java.util.*

class PictureRepositoryImp : PictureRepository, KoinComponent {

    override fun getYesterdayPictures(): Flow<PagingData<FlickrPicture>> {
        return Pager(PagingConfig(pageSize = 20, initialLoadSize = 50)) {
            // I dont really like this class not to be injected, but this is how Google recommends to implement
            // I would inject this somehow, but then it will be hard to set the parameter
            FlickrPicturePagingSource(getYesterdayDateString(), get())
        }.flow
    }

    private fun getYesterdayDateString(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        }
        return formatter.format(date.time)
    }
}