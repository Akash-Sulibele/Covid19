package com.example.covid19app.service

import com.example.covid19app.models.AllCountriesResponseModel
import com.example.covid19app.models.AllCountriesResponseModelItem
import com.example.covid19app.models.HistoricalDataResponseModel
import com.example.covid19app.models.Timeline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceClass {

    val BaseUrl = "https://corona.lmao.ninja/v2/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiEndpoints::class.java)

    fun getContinents(yesterday: Boolean = true, sort: String = "cases", onSuccess: (AllCountriesResponseModel?) -> Unit, onFailure: ()-> Unit) {
        val call = service.getContinents(yesterday, sort)
        call.enqueue(object : Callback<AllCountriesResponseModel> {
            override fun onResponse(call: Call<AllCountriesResponseModel>, response: Response<AllCountriesResponseModel>) {
                if (response.code() == 200) {
                    onSuccess.invoke(response.body())
                }
            }
            override fun onFailure(call: Call<AllCountriesResponseModel>, t: Throwable) {
                onFailure.invoke()
            }
        })
    }

    fun getCountryDetails(yesterday: Boolean = true, strict: Boolean = true,country: String, onSuccess: (AllCountriesResponseModelItem?) -> Unit, onFailure: ()-> Unit) {
        val call = service.getCountryDetails(country,yesterday, strict)
        call.enqueue(object : Callback<AllCountriesResponseModelItem> {
            override fun onResponse(call: Call<AllCountriesResponseModelItem>, response: Response<AllCountriesResponseModelItem>) {
                if (response.code() == 200) {
                    onSuccess.invoke(response.body())
                }
            }
            override fun onFailure(call: Call<AllCountriesResponseModelItem>, t: Throwable) {
                onFailure.invoke()
            }
        })
    }

    fun getCountryHistoricalDetails(lastdays: Int = 10,country: String, onSuccess: (Timeline) -> Unit, onFailure: ()-> Unit) {
        val call = service.getCountryHistoricalDetails(country,lastdays)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.code() == 200) {
                    val jsonObject = JSONObject(Gson().toJson(response.body()))
                    val timeline: JSONObject = jsonObject.getJSONObject("timeline")
                    val cases: String = timeline.getString("cases")
                    val casesMap: LinkedHashMap<String,Int> = Gson().fromJson<LinkedHashMap<String,Int>>(cases, object : TypeToken<LinkedHashMap<String, Int>>() {}.type)
                    val deaths: String = timeline.getString("deaths")
                    val deathsMap: LinkedHashMap<String,Int> = Gson().fromJson<LinkedHashMap<String,Int>>(deaths, object : TypeToken<LinkedHashMap<String, Int>>() {}.type)
                    val recovered: String = timeline.getString("recovered")
                    val recoveredMap: LinkedHashMap<String,Int> = Gson().fromJson<LinkedHashMap<String,Int>>(recovered, object : TypeToken<LinkedHashMap<String, Int>>() {}.type)
                    val timelineData: Timeline = Timeline(casesMap,deathsMap,recoveredMap)
                    onSuccess.invoke(timelineData)
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                onFailure.invoke()
            }
        })
    }

}