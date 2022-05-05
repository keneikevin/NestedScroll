package com.kevin.cakeCity.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.FragmentDescBinding
import com.example.nestedscroll.ui.ShoppingActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_desc.*

class DescFragment : Fragment(R.layout.fragment_desc) {
    val types = arrayOf("2/3", "Finance", "G4", "2/5")

    private lateinit var binding: FragmentDescBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescBinding.bind(view)
        spinner?.adapter = activity?.let {
            ArrayAdapter(
                it,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                types
            )
        } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Snackbar.make(
                    binding.root,
                    "Error",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

        }
        binding.button2.setOnClickListener {
            if (!binding.nameInput.text.isNullOrEmpty()) {
                val intent = Intent(activity, ShoppingActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "Enter your name to proceed",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }


}
