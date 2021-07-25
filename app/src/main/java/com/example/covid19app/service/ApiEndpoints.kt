package com.example.covid19app.service

import com.example.covid19app.models.AllCountriesResponseModel
import com.example.covid19app.models.AllCountriesResponseModelItem
import com.example.covid19app.models.ContinentsResponseModel
import com.example.covid19app.models.HistoricalDataResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("countries?")
    fun getContinents(@Query("yesterday") yesterday: Boolean, @Query("sort") sort: String): Call<AllCountriesResponseModel>

    @GET("countries/{country}?")
    fun getCountryDetails(@Path("country") country: String,@Query("yesterday") yesterday: Boolean, @Query("strict") strict: Boolean): Call<AllCountriesResponseModelItem>

    @GET("historical/{country}?")
    fun getCountryHistoricalDetails(@Path("country") country: String,@Query("lastdays") lastdays: Int): Call<Any>
}