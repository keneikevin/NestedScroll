package com.example.nestedscroll.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nestedscroll.LocationData
import com.example.nestedscroll.data.Item
import com.example.nestedscroll.databinding.ActivityDescBinding
import com.example.nestedscroll.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_desc.*


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescBinding
    lateinit var title: EditText
    lateinit var price: EditText
    lateinit var addUpdateButton: Button
    lateinit var viewModel: CartViewModel
    var itemId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addUpdateButton = binding.button
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            CartViewModel::class.java)


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


        Glide.with(imageview).load(data?.url).into(binding.imageview)
        binding.button.setOnClickListener {
            val title = binding.textViewName.text.toString()
            val price = binding.textViewAddress.text.toString()
            if (data != null) {
                viewModel.addItem(Item(
                    title,price,data.url.toString()
                )
                )
            }

            val intent = Intent(this, ShoppingActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

}


