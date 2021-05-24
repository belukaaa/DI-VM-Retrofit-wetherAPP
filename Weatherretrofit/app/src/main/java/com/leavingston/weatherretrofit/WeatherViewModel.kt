package com.leavingston.weatherretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(private val repo : UserRepository) : ViewModel(), Callback<Weather> {


    private var city : String? = "Tbilisi"
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
    get() = _loadingState

    fun setCityName(city : String?) {

        this.city = city
    }



    private val _data = MutableLiveData<Weather>()
    val data : LiveData<Weather>
    get() = _data



//     fun getCityWeather() {
//        repo.getWeather(city).enqueue(this)
//    }

    init {
        fetchData()
    }

     fun fetchData(){
        _loadingState.postValue(LoadingState.LOADED)
        repo.getWeather(city).enqueue(this)
    }



    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {

        if (response.isSuccessful){
            Log.e("Tag" , "$response")

            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        }else {
            Log.e("Tag" , "$response")

            _loadingState.postValue(LoadingState.error(response.toString()))
        }


    }

    override fun onFailure(call: Call<Weather>, t: Throwable) {

        _loadingState.postValue(LoadingState.error(t.message))
        Log.e("Tag" , "$t")
    }


}