package com.batcuevasoft.flickrinterestingday.extensions

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.io.IOException

@Throws(IOException::class)
fun MockWebServer.enqueueFile(code: Int, fileName: String) {
    val `in` = this.javaClass.classLoader!!.getResourceAsStream(fileName)
    val buffer = `in`.source().buffer()
    val response = MockResponse()
        .setResponseCode(code)
        .setBody(buffer.readUtf8())
    enqueue(response)
}

fun MockWebServer.captureRequest(): RecordedRequest {
    return takeRequest()
}

@Throws(IOException::class)
fun MockWebServer.startWithUrl(): String {
    start()
    return url("/").toString()
}