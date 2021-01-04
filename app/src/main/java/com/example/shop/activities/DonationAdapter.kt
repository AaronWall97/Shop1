package com.example.shop.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_donation.view.*
import com.example.shop.R
import com.example.shop.models.DonationModel

interface DonationListener {
    fun onDonationClick(donation: DonationModel)
}

class DonationAdapter constructor(private var donations: List<DonationModel>,
                                   private val listener: DonationListener) : RecyclerView.Adapter<DonationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_donation, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val donation = donations[holder.adapterPosition]
        holder.bind(donation, listener)
    }

    override fun getItemCount(): Int = donations.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(donation: DonationModel, listener: DonationListener) {
            itemView.donationTitle.text = donation.title
            itemView.description.text = donation.description
            itemView.setOnClickListener { listener.onDonationClick(donation) }
        }
    }
}