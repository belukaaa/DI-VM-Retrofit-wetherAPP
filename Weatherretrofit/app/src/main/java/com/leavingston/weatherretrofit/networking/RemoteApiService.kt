package com.leavingston.weatherretrofit.networking

import com.leavingston.weatherretrofit.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RemoteApiService {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") city : String? , @Query("appid") api : String): Call<Weather>



}