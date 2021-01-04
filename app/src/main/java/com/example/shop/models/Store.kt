package com.example.shop.models

interface Store {
  fun findAll(): List<DonationModel>
  fun create(donation: DonationModel)
  fun update(donation: DonationModel)
  fun delete(donation: DonationModel)
}