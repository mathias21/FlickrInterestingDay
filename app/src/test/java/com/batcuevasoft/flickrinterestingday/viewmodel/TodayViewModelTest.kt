package com.batcuevasoft.flickrinterestingday.viewmodel

import com.batcuevasoft.flickrinterestingday.data.picturerepository.FlickrPictureRepository
import com.batcuevasoft.flickrinterestingday.ui.main.today.TodayViewModel
import com.batcuevasoft.flickrinterestingday.ui.viewmodelutils.CoroutineDispatcherProvider
import com.batcuevasoft.flickrinterestingday.viewmodel.instrumentation.observeOnce
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.koin.dsl.module

// We use Junit5 to avoid using lateinits and to reinstantiate mocks each time
// Reinstantiating mocks is an expensive operation due to reflection usage.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodayViewModelTest : BaseViewModelTest() {

    private val flickrPictureRepository: FlickrPictureRepository = mockk()
    private lateinit var viewModel: TodayViewModel

    @Before
    fun setup() {
        clearMocks(flickrPictureRepository)
        startInjection {
            module {
                // As Google recommends, always always inject your dispatchers
                factory<CoroutineDispatcherProvider>(override = true) { CoroutineProvider() }
            }
        }
        every { flickrPictureRepository.getYesterdayPictures() } returns flow {}
        viewModel = TodayViewModel(flickrPictureRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `when we refresh pictures, we get paging data object`() {
        runBlockingTest {
            viewModel.refreshPictures()
            viewModel.todayPictures.observeOnce {
                // Here we would do paging 3 assertions. Still no docs about how to test this
            }
        }
    }
}

class CoroutineProvider : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Default
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default

}