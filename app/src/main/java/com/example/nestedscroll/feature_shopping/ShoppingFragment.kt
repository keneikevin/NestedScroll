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
import com.example.nestedscroll.databinding.FragmentShoppingBinding

import com.google.android.material.snackbar.Snackbar
import com.kevin.cakeCity.adapters.ShoppingAdapter

class ShoppingFragment :Fragment(R.layout.fragment_shopping) {



    private lateinit var binding: FragmentShoppingBinding
    lateinit var viewModel: CartViewModel
    private lateinit var shoppingAdapter: ShoppingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        binding = FragmentShoppingBinding.bind(view)
        binding.button3.setOnClickListener{
            findNavController().navigate(ShoppingFragmentDirections.actionShoppingFragment2ToOrderFragment())
        }
        subscribeToObservers()
        setupRecyclerView()

        Snackbar.make(
            binding.root,
             "SWIPE TO DELETE",
            Snackbar.LENGTH_SHORT
        ).show()

    }


    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0, LEFT or RIGHT
    ) {
        override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = shoppingAdapter.shoppingItems[pos]
            viewModel?.deleteItem(item)
            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
                setAction("Undo") {
                    viewModel?.addItem(item)
                }
                show()
            }
        }
    }


    private fun subscribeToObservers() {

        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            val price = it ?: 0f
            val priceText = "$price ksh"
            binding.tvCakePrice.text = priceText
        })

        viewModel.allItems.observe(viewLifecycleOwner, Observer {
            shoppingAdapter.shoppingItems = it
        })

    }
    private fun setupRecyclerView() {
        binding.rvShoppingItems.apply {
            shoppingAdapter = ShoppingAdapter()
            adapter = shoppingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }
}
