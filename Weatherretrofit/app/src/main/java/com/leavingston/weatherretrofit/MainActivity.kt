package com.leavingston.weatherretrofit

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.leavingston.weatherretrofit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var mySharedPref: sharedPreferences


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    private val userViewModel by viewModel<WeatherViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val lastCity = binding.CityTextView.text
        binding.currentLocation.text = lastCity






        mySharedPref = sharedPreferences(context = this)





        okey()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        userViewModel.data.observe(this , androidx.lifecycle.Observer {
            Log.e("observable data" , "$it")

            succes(it)
        })
        userViewModel.loadingState.observe(this, androidx.lifecycle.Observer {
            if (it.status.name == "FAILED") {
                if (isNetworkAvailable()) {
                    fail("ადე ააჯმეინე , წესიერი სახელი შეიყვანე ინგლისურად")
                }
                else{
                    fail("ინტერნეტი არ გაქ შე ყლეო ჩართული")
                }
            }
        })

    }

    private fun okey(){
        binding.buttonishe.setOnClickListener {
            val city_name = binding.editTextishe.text.toString()
            binding.currentLocation.visibility = View.GONE
            mySharedPref.saveLastLocation(city_name)
            getSelectedCityWeather(city_name)
            binding.editTextishe.text.clear()

            if (isNetworkAvailable()) {

                userViewModel.fetchData()

                Toast.makeText(applicationContext, "internet is ON ", Toast.LENGTH_SHORT).show()

            } else {

                fail("ინტერნეტი არ გაქ შე ყლეო ჩართული")

                Toast.makeText(applicationContext, "internet is OFF ", Toast.LENGTH_SHORT).show()
            }
        }
    }





        fun isNetworkAvailable(): Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }

        private fun getSelectedCityWeather(string: String) {
            Log.e("Setting City Name", "-> $string")
            userViewModel.setCityName(string)
        }




    private fun fail(fail: String?) {
        binding.Celsius.visibility = View.GONE
        binding.weather.visibility = View.GONE
        binding.currentLocation.visibility = View.VISIBLE

        val lastCity = binding.CityTextView.text
        val lastCityCels = binding.Celsius.text
        binding.currentLocation.text = "ბოლო ნაჩვენები ლოკაცია : " + lastCity + "\n" + lastCityCels

        binding.CityTextView.visibility = View.GONE

        binding.okey.text = fail

    }

    private fun succes(x: Weather?) {
        binding.Celsius.visibility = View.VISIBLE

        binding.weather.visibility = View.VISIBLE

        binding.CityTextView.visibility = View.VISIBLE

        Log.e("City Name", "${x?.name}")
        val kelvin = x?.main?.temp?.toInt()
        val celsius = kelvin?.minus(273)
        val celsius1 = Utils.changeCelsius(celsius)
        binding.Celsius.text = celsius1

        val lat = x?.coord?.lat
        val long = x?.coord?.lon
//            val city = getCIty(lat , long)

        binding.weather.text = x?.weather?.toString()
        Log.e("yleeeooo" , "${x?.weather}")
        binding.CityTextView.text = x?.name
        val id: Long
        val main: String
        val description: String
        var icon: String

        val listOfWeatherElement = x?.weather
        id = listOfWeatherElement?.get(0)?.id!!
        main = listOfWeatherElement[0].description
        description = listOfWeatherElement[0].description

        changeBackground(description)

        val result = Utils.changeWeather(description)



        binding.weather.text = result
        Log.e("yleeeooo" , "${description}")


        binding.okey.text ="ქარი დაწუის " + x.wind.speed.toString() + " კმ/სთ სიჩქარით"
        Log.e("yleeeooo" , "${x?.wind?.speed}")

        title = ("${x?.name} - ს ამინდი")

    }
    fun changeBackground(description : String){
        if(description == "clear sky"){
            binding.background.setBackgroundResource(R.drawable.unnamed)
        }

        if(description == "few clouds"){
            binding.background.setBackgroundResource(R.drawable.avoe)

        }
        if(description == "overcast clouds"){
            binding.background.setBackgroundResource(R.drawable.avoe)

        }
        if(description == "broken clouds"){
            binding.background.setBackgroundResource(R.drawable.avoe)

        }
        if (description == "scattered clouds"){
            binding.background.setBackgroundResource(R.drawable.avoe)

        }
        if (description.contains("snow")){
            binding.background.setBackgroundResource(R.drawable.snowing)
        }
        if (description.contains("rain")){
            binding.background.setBackgroundResource(R.drawable.rain)

        }
    }



}
