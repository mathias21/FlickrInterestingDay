package com.batcuevasoft.flickrinterestingday.datasource.remote

import com.batcuevasoft.flickrinterestingday.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class ServiceBuilder {

    private val clientBuilder = OkHttpClient.Builder()
    private val jsonDateDeserializer = JsonDeserializer<Date> { json, _, _ -> Date(json.asJsonPrimitive.asLong) }

    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .registerTypeAdapter(Date::class.java, jsonDateDeserializer)
        .create()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))


    private fun provideOkHTTPClientWithInterceptors(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        if (BuildConfig.BUILD_TYPE != "release") {
            clientBuilder.addInterceptor(logging)
        }
        return clientBuilder.build()
    }


    fun <T> createService(tClass: Class<T>, baseUrl: String = BuildConfig.BASE_URL): T {
        return retrofitBuilder
            .baseUrl(baseUrl)
            .client(provideOkHTTPClientWithInterceptors())
            .build()
            .create(tClass)
    }

}
