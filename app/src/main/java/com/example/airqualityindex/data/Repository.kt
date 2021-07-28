package com.example.airqualityindex.data

import androidx.lifecycle.MutableLiveData
import com.github.kinnonii.timeago.TimeAgo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.koushikdutta.async.http.AsyncHttpClient
import com.koushikdutta.async.http.AsyncHttpClient.WebSocketConnectCallback
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class Repository {

    var cityDataList: ArrayList<CityData> = ArrayList()
    val liveData: MutableLiveData<ArrayList<CityData>> = MutableLiveData()

    private var url = "ws://city-ws.herokuapp.com/"

    fun fetchCityData(): MutableLiveData<ArrayList<CityData>> {
        AsyncHttpClient.getDefaultInstance().websocket(
            url, "my-protocol",
            WebSocketConnectCallback { ex, webSocket ->
                if (ex != null) {
                    ex.printStackTrace()
                    return@WebSocketConnectCallback
                }
                webSocket.setStringCallback {
                    val time = TimeAgo("en")
                    val current = System.currentTimeMillis()
                    val minutes: String =
                        time.timeAgo(Date(System.currentTimeMillis()))
                    val hours: String = time.timeUntil(Date(System.currentTimeMillis()))

                    cityDataList = ArrayList()
                    val gson = Gson()
                    val type: Type = object : TypeToken<ArrayList<CityData?>?>() {}.type
                    val list: List<CityData> = gson.fromJson(it, type)
                    for (item in list) {
                        val cityData = CityData()
                        cityData.city = item.city
                        cityData.aqi = item.aqi
                        cityData.timeAgo = minutes
                        cityDataList.add(cityData)
                    }
                    liveData.postValue(cityDataList)
                }
                webSocket.setDataCallback { data, byteBufferList ->
                    byteBufferList.recycle()
                }
            })
        return liveData
    }

}