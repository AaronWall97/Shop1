package com.example.shop.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class DonationStore : Store, AnkoLogger {

    val donations = ArrayList<DonationModel>()

    override fun findAll(): List<DonationModel> {
        return donations
    }

    override fun create(donation: DonationModel) {
        donation.id = getId()
        donations.add(donation)
        logAll()
    }

    override fun update(donation: DonationModel) {
        var foundDonation: DonationModel? = donations.find { p -> p.id == donation.id }
        if (foundDonation != null) {
            foundDonation.title = donation.title
            foundDonation.description = donation.description
            foundDonation.lat = donation.lat
            foundDonation.lng = donation.lng
            foundDonation.zoom = donation.zoom
            logAll();
        }
    }

    override fun delete(donation: DonationModel) {
        donations.remove(donation)
    }

    fun logAll() {
        donations.forEach { info("${it}") }
    }
}