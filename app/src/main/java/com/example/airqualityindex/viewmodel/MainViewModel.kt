package com.example.airqualityindex.viewmodel

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.example.airqualityindex.data.CityData
import com.example.airqualityindex.data.Repository

class MainViewModel(context: Context?) : ViewModel() {

    private val cityData: ObservableArrayList<CityData> = ObservableArrayList()
    private val repository: Repository = Repository(context!!)

    fun getAqi(): List<CityData> {
        cityData.addAll(repository.fetchCityData())
        return cityData

    }

}
