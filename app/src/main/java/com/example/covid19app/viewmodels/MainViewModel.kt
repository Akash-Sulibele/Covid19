package com.example.covid19app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.covid19app.models.*
import com.example.covid19app.service.ServiceClass
import java.util.*

class MainViewModel: ViewModel() {
    val service = ServiceClass();
    var continentsResponseModel: MutableList<AllCountriesResponseModelItem> = mutableListOf()
    var selectedCountry = ""
    val list = mutableListOf<String>("All")
    lateinit var timeLineData: Timeline
    lateinit var selectedCountryDetails: AllCountriesResponseModelItem

    fun getContinents(onSuccess: (AllCountriesResponseModel?) -> Unit, onFailure: ()-> Unit) {
        service.getContinents(onSuccess = onSuccess,onFailure = onFailure)
    }

    fun getCountryDetails(onSuccess: (AllCountriesResponseModelItem?) -> Unit, onFailure: ()-> Unit) {
        service.getCountryDetails(country = selectedCountry, onSuccess = onSuccess,onFailure = onFailure)
    }

    fun getCountryHistoricalDetails(onSuccess: (Timeline) -> Unit, onFailure: ()-> Unit) {
        service.getCountryHistoricalDetails(country = selectedCountry, onSuccess = onSuccess,onFailure = onFailure)
    }

    fun processTimeLineData(data: Timeline) {
        timeLineData = data
        var tmap: TreeMap<String, Int> = TreeMap(data.cases)
        timeLineData.cases?.clear()
        timeLineData.cases?.putAll(tmap.descendingMap())
        tmap = TreeMap(data.deaths)
        timeLineData.deaths?.clear()
        timeLineData.deaths?.putAll(tmap.descendingMap())
        tmap= TreeMap(data.recovered)
        timeLineData.recovered?.clear()
        timeLineData.recovered?.putAll(tmap.descendingMap())
    }

}