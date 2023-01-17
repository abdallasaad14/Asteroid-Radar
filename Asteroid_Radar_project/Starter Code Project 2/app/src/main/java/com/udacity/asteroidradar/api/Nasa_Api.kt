package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.API_KEY_Astreo
import com.udacity.asteroidradar.PictureOfDay
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit= Retrofit
    .Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL).build()

interface NASA_API_Service {
    @GET(API_KEY)
    suspend fun getPicOfTheDay(): PictureOfDay
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroid():String

}
object NASA_API {
    val retrofitService:NASA_API_Service by lazy { retrofit.create(NASA_API_Service::class.java) }
}
