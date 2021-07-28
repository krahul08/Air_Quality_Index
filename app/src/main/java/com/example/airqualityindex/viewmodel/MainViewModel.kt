package com.example.airqualityindex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.airqualityindex.data.CityData
import com.example.airqualityindex.data.Repository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    fun getAqi(): MutableLiveData<ArrayList<CityData>> {
        return repository.fetchCityData()
    }

}
