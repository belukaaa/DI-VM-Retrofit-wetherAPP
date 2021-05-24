package com.leavingston.weatherretrofit

import com.leavingston.weatherretrofit.networking.RemoteApiService

class UserRepository(private val api : RemoteApiService) {




    private var API = "4b8c344e1871722dd194de8ac3d8d0dd"

    fun getWeather(city: String?) = api.getWeather(city , API)

}