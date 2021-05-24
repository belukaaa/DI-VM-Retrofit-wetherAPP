package com.leavingston.weatherretrofit.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun buildContent(): OkHttpClient =
    OkHttpClient.Builder()
        .build()

fun buildRetrofit(): Retrofit{
    return Retrofit.Builder()
        .client(buildContent())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

fun buildApiService() : RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)