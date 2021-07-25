package com.example.covid19app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19app.models.AllCountriesResponseModelItem
import com.squareup.picasso.Picasso

class ContinentsAdapter(
    val context: Context,
    val dataSet: List<AllCountriesResponseModelItem>,
    val callback: (String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_continent_row,parent,false)
        return ContinentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder = holder as ContinentsViewHolder
        vHolder.name.text = dataSet[position].country
        vHolder.totalCases.text = dataSet[position].cases?.toString()
        vHolder.totalDeaths.text = dataSet[position].deaths?.toString()
        vHolder.todayCases.text = dataSet[position].todayCases?.toString()
        vHolder.todayDeaths.text = dataSet[position].todayDeaths?.toString()
        vHolder.active.text = dataSet[position].active?.toString()
        vHolder.recovered.text = dataSet[position].recovered?.toString()
        dataSet[position].countryInfo?.flag?.let {
            Picasso.get().load(it).fit().into(vHolder.flagImage)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ContinentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val totalCases: TextView = itemView.findViewById(R.id.total_cases_value)
        val totalDeaths: TextView = itemView.findViewById(R.id.total_deaths_value)
        val todayCases: TextView = itemView.findViewById(R.id.today_cases_value)
        val todayDeaths: TextView = itemView.findViewById(R.id.today_death_value)
        val active: TextView = itemView.findViewById(R.id.total_active_value)
        val recovered: TextView = itemView.findViewById(R.id.total_recovered_value)
        val flagImage: ImageView = itemView.findViewById(R.id.country_flag_iv)

        init {
            itemView.setOnClickListener {
                callback.invoke(dataSet[adapterPosition].country ?: "")
            }
        }
    }
}