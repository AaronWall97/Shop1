package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_donation_list.*
import kotlinx.android.synthetic.main.card_donation.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import com.example.shop.R
import com.example.shop.main.MainActivity
import com.example.shop.models.DonationModel

class DonationListActivity : AppCompatActivity(), DonationListener {

    lateinit var app: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_list)
        app = application as MainActivity
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = DonationAdapter(app.donations.findAll(), this)
        loadDonations()
    }

    private fun loadDonations() {
        showDonations( app.donations.findAll())
    }

    fun showDonations (donations: List<DonationModel>) {
        recyclerView.adapter = DonationAdapter(donations, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<DonationActivity>(200)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDonationClick(donation: DonationModel) {
        startActivityForResult(intentFor<DonationActivity>().putExtra("donation_edit", donation), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadDonations()
        super.onActivityResult(requestCode, resultCode, data)
    }
}