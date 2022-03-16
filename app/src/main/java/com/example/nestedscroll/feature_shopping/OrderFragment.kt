package com.kevin.cakeCity.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.FragmentDetailBinding
import com.example.nestedscroll.databinding.FragmentOrderBinding
import com.example.nestedscroll.other.Status
import com.example.nestedscroll.viewmodel.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar

class OrderFragment: Fragment(R.layout.fragment_order) {

    private lateinit var binding: FragmentOrderBinding
    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentOrderBinding.bind(view)
        subscribeToObservers()


    binding.button4.setOnClickListener {
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHomeFragment())
    }


    }


    private fun subscribeToObservers(){


        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "An unknown error occurred",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Status.SUCCESS -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "Added Shopping Item",
                            Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoppingFragment2())
                    }
                    Status.LOADING -> {
                        /*NO OP*/
                    }
                }
            }
        }
        )

    }

    override fun onPause() {
        super.onPause()
        viewModel.setCurPrice("")
    }

}






















