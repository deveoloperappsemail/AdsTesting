package com.adslib

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

object InternetConnection {
    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    fun checkConnection(context: Context): Boolean {
        val connMgr =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetworkInfo = connMgr.activeNetworkInfo
        return if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                Log.d("TAG", "checkConnection: true")
                true
            } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        } else false
    }
}