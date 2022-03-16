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
import com.example.nestedscroll.other.Status
import com.example.nestedscroll.viewmodel.ShoppingViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    lateinit var viewModel: ShoppingViewModel
    private val args:DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentDetailBinding.bind(view)
        subscribeToObservers()


        Glide.with(this.requireContext())
            .load(args.currentCake.image).into(binding.bigImage)

        args.currentCake.price.let {
            viewModel.setCurPrice(it.toString())
        }

        binding.etShoppingItemName.text = args.currentCake.Name
        binding.details.text = args.currentCake.details
        binding.price.text = args.currentCake.price.toString()



        binding.btnAddShoppingItem.setOnClickListener {
            viewModel.insertShoppingItem(
                binding.etShoppingItemName.text.toString(),
                binding.details.text.toString(),
                binding.price.text.toString(),
                args.currentCake.image.toString()
            )

        }
    }


    private fun subscribeToObservers(){
        viewModel.cake.observe(viewLifecycleOwner, Observer {
            it
        })
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->

         //   binding.price.text = newScore.toString()

        })
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






















