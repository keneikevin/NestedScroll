package com.example.nestedscroll.adapters

import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.CardBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nestedscroll.data.Menu
import com.example.nestedscroll.feature_shopping.HomeFragmentDirections


class CakeAdapter:  PagingDataAdapter<Menu, CakeAdapter.CakeViewHolder>(Companion),Filterable {


    var photosList: ArrayList<Menu> = ArrayList()
    var photosListFiltered: ArrayList<Menu> = ArrayList()

    class CakeViewHolder(val binding: CardBinding, var cake: Menu? = null) : RecyclerView.ViewHolder(
        binding.root){
        val tvTitle= binding.textName
        val tvPrice= binding.textPrice
        val ivCake = binding.img
    }
    companion object : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem.price == newItem.price
        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val cake = getItem(position) ?:return



        holder.apply {
            Glide.with(itemView).setDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_image)
                .dontAnimate()
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA))
                .load(cake.image).into(ivCake)

            itemView.setOnClickListener {
                val directions=HomeFragmentDirections.actionHomeFragmentToDetailFragment(cake)
                it.findNavController().navigate(directions)
            }

            tvTitle.text = cake.Name

            tvPrice.text = cake.price.toString()


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        return CakeViewHolder(
            CardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    fun addData(list: List<Menu>) {
        photosList = list as ArrayList<Menu>
        photosListFiltered = photosList
        notifyDataSetChanged()
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) photosListFiltered = photosList else {
                    val filteredList = ArrayList<Menu>()
                    photosList
                        .filter {
                            (it.price.toString().contains(constraint!!)) or
                                    (it.Name?.contains(constraint) == true)

                        }
                        .forEach { filteredList.add(it) }
                    photosListFiltered = filteredList

                }
                return FilterResults().apply { values = photosListFiltered }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                photosListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Menu>
                notifyDataSetChanged()
            }

        }
    }
}







