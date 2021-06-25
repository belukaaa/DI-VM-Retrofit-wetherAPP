package com.leavingston.weatherretrofit

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import com.leavingston.weatherretrofit.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mySharedPref: sharedPreferences


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    private val userViewModel by viewModel<WeatherViewModel>()

    private lateinit var binding: ActivityMainBinding

//    val options = FirebaseTranslatorOptions.Builder()
//        .setSourceLanguage(FirebaseTranslateLanguage.EN)
//        .setTargetLanguage(FirebaseTranslateLanguage.KA)
//        .build()
//    val englishGermanTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)

//    var options = FirebaseTranslatorOptions.Builder()
//        .setSourceLanguage(FirebaseTranslateLanguage.EN)
//        .setTargetLanguage(FirebaseTranslateLanguage.KA)
//        .build()
//
//    var Translator = FirebaseNaturalLanguage.getInstance().getTranslator(options)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editTextishe, InputMethodManager.SHOW_IMPLICIT)
        val lastCity = binding.CityTextView.text
        binding.currentLocation.text = lastCity



//       val ave = Translator.translate("fasfasf")
//        Log.i("agasgasg", "$ave")



        mySharedPref = sharedPreferences(context = this)





        okey()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        userViewModel.data.observe(this, androidx.lifecycle.Observer {
            Log.e("observable data", "$it")

            succes(it)
        })
        userViewModel.loadingState.observe(this, androidx.lifecycle.Observer {
            if (it.status.name == "FAILED") {
                if (isNetworkAvailable()) {
                    fail("ადე ააჯმეინე , წესიერი სახელი შეიყვანე ინგლისურად")
                } else {
                    fail("ინტერნეტი არ გაქ შე ყლეო ჩართული")
                }
            }
        })

    }

    private fun okey(){
        binding.buttonishe.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editTextishe, InputMethodManager.HIDE_IMPLICIT_ONLY)
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
        binding.background.setBackgroundResource(R.drawable.error)

        binding.Celsius.visibility = View.INVISIBLE
        binding.weather.visibility = View.INVISIBLE
        binding.currentLocation.visibility = View.VISIBLE

        val lastCity = binding.CityTextView.text
        val lastCityCels = binding.Celsius.text
        binding.currentLocation.text = "ბოლო ნაჩვენები ლოკაცია : " + lastCity + "\n" + lastCityCels

        binding.CityTextView.visibility = View.INVISIBLE

        binding.okey.text = fail

    }

    private fun succes(x: Weather?) {
        binding.Celsius.visibility = View.VISIBLE

        binding.weather.visibility = View.VISIBLE

        binding.CityTextView.visibility = View.VISIBLE

        Log.e("City Name", "${x?.name}")
        val kelvin = x?.main?.temp?.toInt()
        val kelvinToCelsius = kelvin?.minus(273)
        val celsius1 = Utils.changeCelsius(kelvinToCelsius)
        binding.Celsius.text = celsius1

        val lat = x?.coord?.lat
        val long = x?.coord?.lon
//            val city = getCIty(lat , long)

        binding.weather.text = x?.weather?.toString()
        Log.e("yleeeooo", "${x?.weather}")
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


        binding.okey.text ="ქარი დაწუის " + x.wind.speed.toString() + " კმ/სთ სიჩქარით"

    }
    fun changeBackground(description: String ){
        if(description == "clear sky" ){
            binding.background.setBackgroundResource(R.drawable.unnamed)
        }

        if(description == "few clouds"){
            binding.background.setBackgroundResource(R.drawable.few_clouds)

        }
        if(description == "overcast clouds"){
            binding.background.setBackgroundResource(R.drawable.overcast_clouds)

        }
        if(description == "broken clouds"){
            binding.background.setBackgroundResource(R.drawable.overcast_clouds)

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
//    fun translate(inputText : String){
//        val options: FirebaseTranslatorOptions = RemoteInput.Builder()
//            .setSourceLanguage(FirebaseTranslateLanguage.EN)
//            .setTargetLanguage(FirebaseTranslateLanguage.KA)
//            .build()
//        val Translator: FirebaseTranslator =
//            FirebaseNaturalLanguage.getInstance().getTranslator(options)
//
//        Translator.translate(inputText)
//            .addOnSuccessListener(
//                OnSuccessListener<String> { })
//            .addOnFailureListener(
//                OnFailureListener { })
//    }





}
