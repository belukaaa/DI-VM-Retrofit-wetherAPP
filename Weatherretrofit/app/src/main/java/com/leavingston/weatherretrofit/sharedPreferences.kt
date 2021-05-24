package com.leavingston.weatherretrofit

import android.content.Context

class sharedPreferences(context:Context) {

    private val City_Key = "Tbilisi"
    private val KEY = "Key"
    private val pref = context.getSharedPreferences(City_Key , Context.MODE_PRIVATE)
    private val editor = pref.edit()




    fun saveLastLocation(city : String){
        editor.putString(City_Key , city)
        editor.apply()
    }
    fun getLastLocation() : String {
        return pref.getString(City_Key,"Tbilisi")!!
    }

}