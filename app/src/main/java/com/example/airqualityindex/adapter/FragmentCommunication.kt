package com.example.airqualityindex.adapter

interface FragmentCommunication {
    fun respond(city: String?, aqi: Double?, timeAgo: String?)
}