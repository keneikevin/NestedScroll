package com.example.nestedscroll.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nestedscroll.LocationData
import com.example.nestedscroll.R
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class NestedRecyClerView(val clickListener: OnRecyclerItemClick) :RecyclerView.Adapter<NestedRecyClerView.MyViewHolder>() {

    var Data = mutableListOf<LocationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(inflater, clickListener)
    }

    override fun getItemCount(): Int {
        return Data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(Data[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClickListener(Data[position])
        }
    }

    class MyViewHolder(view: View, val clickListener: OnRecyclerItemClick): RecyclerView.ViewHolder(view) {
        val imageview = view.imageview
        val textViewName = view.textViewName
        val textViewAddress = view.textViewAddress
        val tvChildCount = view.tvChildCount
        val childRecyclerView = view.childRecyclerView

        fun bind(data: LocationData) {
            textViewName.text = data.Name
            var address = ""

            data.subtitle?.let {
                address += it+", "
            }
            
            data.price?.let {
                address += it+"Ksh"
            }
            textViewAddress.text = address


            Glide.with(imageview).load(data.url).into(imageview)

            if(data.childLocations != null && data.childLocations.size > 0) {

                childRecyclerView.visibility =  VISIBLE
                childRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    val recyclerViewAdapter  = NestedRecyClerView(clickListener)
                    recyclerViewAdapter.Data = data.childLocations.toMutableList()
                    adapter = recyclerViewAdapter

                }
                tvChildCount.text = "${data.childLocations.size} items"
            } else {
                tvChildCount.visibility = GONE
                childRecyclerView.visibility =  GONE
            }

        }
    }

    interface  OnRecyclerItemClick {
        fun onItemClickListener(data: LocationData)
    }

}