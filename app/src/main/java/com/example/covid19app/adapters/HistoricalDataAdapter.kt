package com.example.covid19app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19app.R
import com.example.covid19app.models.Timeline

class HistoricalDataAdapter(
    val context: Context,
    val dataSet: Timeline
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.historical_data_row,parent,false)
        return HistoricalDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vHolder = holder as HistoricalDataViewHolder
        vHolder.cases.text = dataSet.cases?.values?.toIntArray()?.get(position).toString()
        vHolder.deaths.text = dataSet.deaths?.values?.toIntArray()?.get(position).toString()
        vHolder.recovered.text = dataSet.recovered?.values?.toIntArray()?.get(position).toString()
        vHolder.date.text = dataSet.cases?.keys?.toTypedArray()?.get(position).toString()
    }

    override fun getItemCount(): Int {
        return dataSet.cases?.size ?: 0
    }

    inner class HistoricalDataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cases: TextView = itemView.findViewById(R.id.cases_tv)
        val deaths: TextView = itemView.findViewById(R.id.deaths_tv)
        val recovered: TextView = itemView.findViewById(R.id.recovered_tv)
        val date: TextView = itemView.findViewById(R.id.date_value_tv)
    }
}