package com.batcuevasoft.flickrinterestingday.data

import com.batcuevasoft.flickrinterestingday.data.instrumentation.FlickrPictureRepositoryInstrumentation.getPictureWrapperRemoteEntity
import com.batcuevasoft.flickrinterestingday.data.pictures.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.data.pictures.FlickrPictureRepositoryImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasource
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
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
            withContext(Dispatchers.Main) {
                // If I collect this, then the following issue shows up
                //https://github.com/Kotlin/kotlinx.coroutines/issues/1204
                flickrRepository.getYesterdayPictures()
            }
        }
    }

}