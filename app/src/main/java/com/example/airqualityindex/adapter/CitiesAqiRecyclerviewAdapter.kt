package com.example.airqualityindex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualityindex.R
import com.example.airqualityindex.data.CityData
import com.example.airqualityindex.databinding.ItemCityListBinding

class CitiesAqiRecyclerviewAdapter : RecyclerView.Adapter<CitiesAqiViewHolder>() {
    private lateinit var cityList: List<CityData>

    fun setData(cityList: List<CityData>) {
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
        holder.onBind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}

class CitiesAqiViewHolder(private val binding: ItemCityListBinding) : RecyclerView.ViewHolder(
    binding.root
) {
    fun onBind(cityData: CityData) {
        binding.textCity.text = cityData.city
        binding.textQualityIndex.text = cityData.aqi.toString()
        binding.executePendingBindings()
    }

}
