package com.leavingston.weatherretrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leavingston.weatherretrofit.networking.BASE_URL
import com.leavingston.weatherretrofit.networking.RemoteApiService
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    var retrofitInstance : Retrofit? = null

    viewModel {
        WeatherViewModel(get())
    }

    single { UserRepository(get()) }

    fun provideUseApi(retrofit: Retrofit) : RemoteApiService{
        return  retrofit.create((RemoteApiService::class.java))
    }

    single {
        provideUseApi(get())
    }

    fun provideGson() : Gson {
        return  GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }
    fun provideHttpClient() : OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
        return  okHttpClient.build()
    }


    fun provideRetrofit(factory : Gson , client: OkHttpClient) : Retrofit {
        return if (retrofitInstance == null){
            retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()
            retrofitInstance as Retrofit
        }
        else retrofitInstance as Retrofit

    }

    single { provideGson() }
    single {provideHttpClient()}
    single { provideRetrofit(get() , get()) }
}

