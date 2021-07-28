package com.example.airqualityindex.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class CityData : BaseObservable() {

    @get:Bindable
    var city: String? = null

    @get:Bindable
    var aqi: Double? = null

    @get:Bindable
    var timeAgo: String? = null


}
