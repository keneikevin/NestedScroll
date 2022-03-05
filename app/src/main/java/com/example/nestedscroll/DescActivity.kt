package com.example.nestedscroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_desc.*

class DescActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        val data = intent.getParcelableExtra<LocationData>("loc_data")

        textViewName.text = data?.Name

        var address = ""

        data?.subtitle?.let {
            address += it +", "
        }

        data?.price?.let {
            address += it
        }
        textViewAddress.text = address


        Glide.with(imageview).load(data?.url).into(imageview)
    }

}