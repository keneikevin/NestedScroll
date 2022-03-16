package com.example.nestedscroll.feature_shopping



import android.os.Bundle
import android.view.SearchEvent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nestedscroll.R
import com.example.nestedscroll.adapters.CakeAdapter
import com.example.nestedscroll.databinding.FragmentHomeBinding
import com.example.nestedscroll.viewmodel.ShoppingViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment :Fragment(R.layout.fragment_home){

    lateinit var cakeAdapter: CakeAdapter
    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModel : ShoppingViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
        binding = FragmentHomeBinding.bind(view)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView()= binding.rvCakes.apply{
        cakeAdapter=CakeAdapter()
        binding.rvCakes.adapter = cakeAdapter
        binding.rvCakes.layoutManager = GridLayoutManager(requireContext(), 2)
        itemAnimator = null

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                cakeAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cakeAdapter.filter.filter(newText)
                return false
            }

        })
        lifecycleScope.launch {
            viewModel.flow.collect {
                cakeAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            cakeAdapter.loadStateFlow.collectLatest {
                binding.progressBar.isVisible = it.refresh is LoadState.Loading ||
                        it.append is LoadState.Loading
            }
        }
    }

}









