package com.example.airqualityindex.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualityindex.R
import com.example.airqualityindex.data.CityData
import com.example.airqualityindex.databinding.ItemCityListBinding

class CitiesAqiRecyclerviewAdapter(
    var requireContext: Context,
    private var fragmentCommunication: FragmentCommunication
) :
    RecyclerView.Adapter<CitiesAqiRecyclerviewAdapter.CitiesAqiViewHolder>() {
    private lateinit var cityList: ArrayList<CityData>

    fun setData(cityList: ArrayList<CityData>) {
        this.cityList = cityList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CitiesAqiViewHolder {
        val binding: ItemCityListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context), R.layout.item_city_list,
            viewGroup, false
        )
        return CitiesAqiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitiesAqiViewHolder, position: Int) {
        holder.onBind(cityList[position], requireContext, fragmentCommunication)
    }
    override fun getItemCount(): Int {
        return cityList.size
    }

class CitiesAqiViewHolder(
    val binding: ItemCityListBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    fun onBind(
        cityData: CityData,
        requireContext: Context,
        fragmentCommunication: FragmentCommunication
    ) {

        binding.textCity.text = cityData.city
        val decimalNumber = String.format("%.2f", cityData.aqi)
        Log.d("Rahul Debug", "" + decimalNumber)

        binding.positionBasedCard.setOnClickListener {
            fragmentCommunication.respond(
                cityData.city.toString(),
                decimalNumber,
                cityData.timeAgo.toString()
            )
        }
        when {
            cityData.aqi!! < 51 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.good
                    )
                )
            }
            cityData.aqi!! in 51.0..100.0 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.satisfactory
                    )
                )
            }
            cityData.aqi!! in 101.0..200.0 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.moderate
                    )
                )
            }
            cityData.aqi!! in 201.0..300.0 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.poor
                    )
                )
            }
            cityData.aqi!! in 301.0..400.0 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.very_poor
                    )
                )
            }
            cityData.aqi!! in 401.0..500.0 -> {
                binding.textQualityIndex.setTextColor(
                    ContextCompat.getColor(
                        requireContext,
                        R.color.severe
                    )
                )
            }
        }
        binding.textQualityIndex.text = decimalNumber
        binding.textTime.text = cityData.timeAgo
        binding.executePendingBindings()
    }
}
}

interface FragmentCommunication {
    fun respond(city: String, aqi: String, timeAgo: String)
}

