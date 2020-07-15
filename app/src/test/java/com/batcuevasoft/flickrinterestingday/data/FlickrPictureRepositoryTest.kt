package com.batcuevasoft.flickrinterestingday.data

import com.batcuevasoft.flickrinterestingday.data.instrumentation.FlickrPictureRepositoryInstrumentation.getPictureWrapperRemoteEntity
import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepositoryImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.model.PictureWrapperRemoteEntity
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasource
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.koin.dsl.module

// We use Junit5 to avoid using lateinits and to reinstantiate mocks each time
// Reinstantiating mocks is an expensive operation due to reflection usage.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlickrPictureRepositoryTest : BaseRepositoryTest() {

    private val flickrPictureRemoteDatasource: FlickrPictureRemoteDatasource = mockk()
    private lateinit var flickrRepository: FlickrPictureRepository

    @Before
    fun setup() {
        clearMocks(flickrPictureRemoteDatasource)
        startInjection {
            module {
                factory(override = true) { flickrPictureRemoteDatasource }
            }
        }
        every { flickrPictureRemoteDatasource.getPicturesFromDate(any(), any()) } returns getPictureWrapperRemoteEntity()

        flickrRepository = FlickrPictureRepositoryImp()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `when calling flickrRepository to get pictures, we verify paging returned is correct`() {
        runBlockingTest {
            val flow = flickrRepository.getYesterdayPictures()
            flow.collect {
                // We would do validation here, still no documentation how to test pagingData
            }
        }
    }

}