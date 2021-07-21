package com.example.airqualityindex.data

import android.content.Context
import android.util.Log
import com.koushikdutta.async.http.AsyncHttpClient
import com.koushikdutta.async.http.AsyncHttpClient.WebSocketConnectCallback


class Repository(private val context: Context) {

    private val cityDataList: ArrayList<CityData> = ArrayList()
    private var url = "ws://city-ws.herokuapp.com/"

    fun fetchCityData(): ArrayList<CityData> {

        AsyncHttpClient.getDefaultInstance().websocket(url, "my-protocol",
            WebSocketConnectCallback { ex, webSocket ->
                if (ex != null) {
                    ex.printStackTrace()
                    return@WebSocketConnectCallback
                }

                webSocket.setStringCallback {
                    Log.d("feefefefe", it)
                }
                webSocket.setDataCallback { emitter, bb ->
                    bb.recycle()
                }
            })




        return cityDataList
    }

}