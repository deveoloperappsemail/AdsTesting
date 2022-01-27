package com.adstesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adslib.LiveAds
import com.adslib.TestAds


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LiveAds().getLiveAds(this, packageName)
        TestAds().getTestAds(this)
    }
}