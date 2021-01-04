package com.example.shop.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import com.example.shop.helpers.*
import java.util.*

val JSON_FILE = "donation.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<DonationModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class DonationJSONStore : Store, AnkoLogger {

    val context: Context
    var donations = mutableListOf<DonationModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<DonationModel> {
        return donations
    }

    override fun create(donation: DonationModel) {
        donation.id = generateRandomId()
        donations.add(donation)
        serialize()
    }

    override fun update(donation: DonationModel) {
        val donationsList = findAll() as ArrayList<DonationModel>
        var foundDonation: DonationModel? = donationsList.find { p -> p.id == donation.id }
        if (foundDonation != null) {
            foundDonation.title = donation.title
            foundDonation.description = donation.description
            foundDonation.lat = donation.lat
            foundDonation.lng = donation.lng
            foundDonation.zoom = donation.zoom
        }
        serialize()
    }

    override fun delete(donation: DonationModel) {
        donations.remove(donation)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(donations, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        donations = Gson().fromJson(jsonString, listType)
    }
}