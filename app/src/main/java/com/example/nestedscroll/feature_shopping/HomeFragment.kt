package com.kevin.cakeCity.ui.main.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedscroll.viewmodel.CartViewModel
import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.FragmentHomeBinding
import com.example.nestedscroll.databinding.FragmentIntroBinding
import com.example.nestedscroll.databinding.FragmentShoppingBinding

import com.google.android.material.snackbar.Snackbar
import com.kevin.cakeCity.adapters.ShoppingAdapter

class HomeFragment :Fragment(R.layout.fragment_home) {



    private lateinit var binding: FragmentHomeBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)


    }
}
