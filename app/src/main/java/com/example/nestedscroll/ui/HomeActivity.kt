package com.example.nestedscroll.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nestedscroll.LocationData
import com.example.nestedscroll.MenuList
import com.example.nestedscroll.R
import com.example.nestedscroll.adapters.NestedRecyClerView
import com.example.nestedscroll.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), NestedRecyClerView.OnRecyclerItemClick {

    lateinit var nestedRecyClerView: NestedRecyClerView
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            nestedRecyClerView = NestedRecyClerView(this@HomeActivity)
            adapter =nestedRecyClerView
        }
    }


    private fun loadData() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getListObervable().observe(this, Observer<MenuList> {
            nestedRecyClerView.Data = it.data.toMutableList()
            nestedRecyClerView.notifyDataSetChanged()
        })
        viewModel.loadData(this@HomeActivity)
    }

    override fun onItemClickListener(data: LocationData) {
        val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
        intent.putExtra("loc_data", data)

        startActivity(intent)
    }
}