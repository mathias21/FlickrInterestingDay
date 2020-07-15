package com.batcuevasoft.flickrinterestingday.datasource.remote


import com.batcuevasoft.flickrinterestingday.datasource.remote.instrumentation.ServerFixtures
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasource
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureRemoteDatasourceImp
import com.batcuevasoft.flickrinterestingday.datasource.remote.picture.FlickrPictureService
import com.batcuevasoft.flickrinterestingday.extensions.enqueueFile
import com.batcuevasoft.flickrinterestingday.extensions.startWithUrl
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

class FlickrPictureRemoteDatasourceTest {

    private lateinit var mockServer: MockWebServer
    private lateinit var remoteDataSource: FlickrPictureRemoteDatasource

    @Before
    fun before() {
        mockServer = MockWebServer()
        val baseUrl = mockServer.startWithUrl()
        remoteDataSource = FlickrPictureRemoteDatasourceImp(
            ServiceBuilder().createService(FlickrPictureService::class.java, baseUrl = baseUrl))
    }

    @Test
    fun given_a_valid_response_from_server_we_parse_properly() {

        mockServer.enqueueFile(200, ServerFixtures.SUCCESS_RESPONSE)

        val response = remoteDataSource.getPicturesFromDate("2020-05-05", 1)

        Assertions.assertThat(response.photos.photo.size).isEqualTo(10)

//         If we want to make assertions over request
//        with(mockServer.captureRequest()) {
//            Assertions.assertThat(this)
//        }

    }
}