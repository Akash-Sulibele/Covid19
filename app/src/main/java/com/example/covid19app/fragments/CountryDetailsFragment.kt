package com.example.covid19app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19app.R
import com.example.covid19app.adapters.HistoricalDataAdapter
import com.example.covid19app.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CountryDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountryDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var name: TextView
    private lateinit var totalCases: TextView
    private lateinit var totalDeaths: TextView
    private lateinit var todayCases: TextView
    private lateinit var todayDeaths: TextView
    private lateinit var active: TextView
    private lateinit var recovered: TextView
    private lateinit var countryFlag: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalDataAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CountryDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CountryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        name = itemView.findViewById(R.id.name)
        totalCases = itemView.findViewById(R.id.total_cases_value)
        totalDeaths = itemView.findViewById(R.id.total_deaths_value)
        todayCases  = itemView.findViewById(R.id.today_cases_value)
        todayDeaths = itemView.findViewById(R.id.today_death_value)
        active = itemView.findViewById(R.id.total_active_value)
        recovered = itemView.findViewById(R.id.total_recovered_value)
        countryFlag = itemView.findViewById(R.id.country_flag_iv)
        recyclerView = itemView.findViewById(R.id.historical_data_rv)
        setupRecyclerView()

        name.text = viewModel.selectedCountryDetails.country
        totalCases.text = viewModel.selectedCountryDetails.cases.toString()
        totalDeaths.text = viewModel.selectedCountryDetails.deaths.toString()
        todayCases.text = viewModel.selectedCountryDetails.todayCases.toString()
        todayDeaths.text = viewModel.selectedCountryDetails.todayDeaths.toString()
        active.text = viewModel.selectedCountryDetails.active.toString()
        recovered.text = viewModel.selectedCountryDetails.recovered.toString()
        viewModel.selectedCountryDetails.countryInfo?.flag?.let {
            Picasso.get().load(it).fit().into(countryFlag)
        }
    }

    private fun setupRecyclerView() {
        activity?.baseContext?.let {
            adapter = HistoricalDataAdapter(it,viewModel.timeLineData)
            val layoutManager = LinearLayoutManager(it)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}