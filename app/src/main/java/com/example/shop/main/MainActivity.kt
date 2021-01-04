package com.example.shop.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import com.example.shop.models.DonationJSONStore
import com.example.shop.models.Store
import com.example.shop.models.DonationStore

class MainApp : Application(), AnkoLogger {

    lateinit var donation: Store

    override fun onCreate() {
        super.onCreate()
        donation = DonationJSONStore(applicationContext)
        info("Donation created")
    }
}