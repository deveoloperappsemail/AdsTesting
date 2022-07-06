package com.adslib

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class TestAds {
    fun getTestAds(context: Context) {
        if (InternetConnection.checkConnection(context)) {
            fetchData(context)
        } else {
            storeAds(context)
        }
    }

    fun testFunction() {
        val string = "hello"
    }

    private fun storeAds(context: Context) {
        val appid: String = SharePreferenceUtils.getStringData(context, Constants.APP_ID).toString()
        if (appid == null) {
            SharePreferenceUtils.saveData(context, Constants.APP_ID, "no")
        }
        val inters: String =
            SharePreferenceUtils.getStringData(context, Constants.INTERSTIAL).toString()
        if (inters == null) {
            SharePreferenceUtils.saveData(context, Constants.INTERSTIAL, "no")
        }
        val banner: String =
            SharePreferenceUtils.getStringData(context, Constants.BANNER).toString()
        if (banner == null) {
            SharePreferenceUtils.saveData(context, Constants.BANNER, "no")
        }
        val native1: String =
            SharePreferenceUtils.getStringData(context, Constants.NATIVE_AD).toString()
        if (native1 == null) {
            SharePreferenceUtils.saveData(context, Constants.NATIVE_AD, "no")
        }
        val openad: String =
            SharePreferenceUtils.getStringData(context, Constants.OPEN_AD).toString()
        if (openad == null) {
            SharePreferenceUtils.saveData(context, Constants.OPEN_AD, "no")
        }
    }

    private fun fetchData(context: Context) {
        val baseUrl =
            "https://adstesting.toptrendingappstudio.com/fetchtestads.php"
        val queue = Volley.newRequestQueue(context)
        val postRequest: StringRequest = object : StringRequest(
            Method.GET, baseUrl,
            Response.Listener { response -> // response
                Log.d("TAG", response)
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        SharePreferenceUtils.saveData(
                            context,
                            Constants.APP_ID,
                            jsonObject.getString("appid")
                        )
                        SharePreferenceUtils.saveData(
                            context,
                            Constants.INTERSTIAL,
                            jsonObject.getString("inter")
                        )
                        SharePreferenceUtils.saveData(
                            context,
                            Constants.BANNER,
                            jsonObject.getString("banner")
                        )
                        SharePreferenceUtils.saveData(
                            context,
                            Constants.NATIVE_AD,
                            jsonObject.getString("native")
                        )
                        SharePreferenceUtils.saveData(
                            context,
                            Constants.OPEN_AD,
                            jsonObject.getString("openad")
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    storeAds(context)
                }
            },
            Response.ErrorListener { // error
                Log.d("", "Error.Response")
                storeAds(context)
            }
        ) {

        }

        queue.add(postRequest)
    }
}