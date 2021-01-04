package com.example.shop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_donation.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import com.example.shop.R
import com.example.shop.main.MainActivity
import com.example.shop.models.DonationModel


class DonationActivity : AppCompatActivity(), AnkoLogger {

    var donation = DonationModel()
    lateinit var app: MainActivity
    var edit = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Donation Activity started..")

        app = application as MainActivity

        if (intent.hasExtra("Donation_edit")) {
            edit = true
            donation = intent.extras?.getParcelable<DonationModel>("Donation_edit")!!
            donationTitle.setText(donation.title)
            description.setText(donation.description)
            btnAdd.setText(R.string.save_donation)
        }

        btnAdd.setOnClickListener() {
            donation.title = donationTitle.text.toString()
            donation.description = description.text.toString()
            if (donation.title.isEmpty()) {
                toast(R.string.enter_donation_title)
            } else {
                if (edit) {
                    app.donations.update(donation.copy())
                } else {

                    app.donations.create(donation.copy())
                }
            }
            info("add Button Pressed: $donationTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }


    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_donation, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


        fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.donations.delete(donation)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

     fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

                }
            }
        }
    }
