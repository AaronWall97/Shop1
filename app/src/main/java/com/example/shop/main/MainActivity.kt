package com.example.shop.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import com.example.shop.models.DonationJSONStore
import com.example.shop.models.Store
import com.example.shop.models.DonationStore

class MainActivity : Application(), AnkoLogger {

    lateinit var donations: Store

    override fun onCreate() {
        super.onCreate()
        donations = DonationJSONStore(applicationContext)
        info("Donation created")
    }
}