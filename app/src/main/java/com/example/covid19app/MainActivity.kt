package com.example.covid19app

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.covid19app.viewmodels.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var viewModel: MainViewModel
    val factory: ViewModelProvider.Factory = NewInstanceFactory()
    lateinit var spinner: Spinner
    lateinit var loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        spinner = findViewById(R.id.spinner)
        loader = findViewById(R.id.progressBar_cyclic)
        setupSpinner()
    }

    private fun setupSpinner() {
        spinner.onItemSelectedListener = this
        val adapter = ArrayAdapter<String>(this,R.layout.spinner_item_layout,viewModel.list)
        loader.visibility = View.VISIBLE
        viewModel.getContinents({
            it?.forEach {
                viewModel.continentsResponseModel.add(it)
                viewModel.list.add(it.country ?: "")
            }
            spinner.adapter = adapter
            val fragment = ContinentsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout,fragment,"continents")
            transaction.commit()
            loader.visibility = View.GONE
        }){
            loader.visibility = View.GONE
        }

    }

    public fun onItemSelected(country: String) {
        spinner.setSelection(viewModel.list.indexOf(country))
        viewModel.selectedCountry = country
        loader.visibility = View.VISIBLE
        viewModel.getCountryDetails({
            it?.let {
                viewModel.selectedCountryDetails = it
                viewModel.getCountryHistoricalDetails({
                    viewModel.processTimeLineData(it)
                    val fragment = CountryDetailsFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame_layout,fragment,"Country details")
                    transaction.commit()
                    loader.visibility = View.GONE
                }){
                    loader.visibility = View.GONE
                }
            }
        }){
            loader.visibility = View.GONE
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 != 0) {
            onItemSelected(viewModel.list[p2])
        } else {
            val fragment = ContinentsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout,fragment,"continents")
            transaction.commit()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}