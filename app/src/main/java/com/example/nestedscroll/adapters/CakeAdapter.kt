package com.example.nestedscroll.adapters

import com.example.nestedscroll.R
import com.example.nestedscroll.databinding.CardBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nestedscroll.data.Cake


class CakeAdapter:  PagingDataAdapter<Cake, CakeAdapter.CakeViewHolder>(Companion) {
    class CakeViewHolder(val binding: CardBinding, var cake: Cake? = null) : RecyclerView.ViewHolder(
        binding.root){
        val tvTitle= binding.textName
        val tvPrice= binding.textPrice
        val ivCake = binding.img
    }
    companion object : DiffUtil.ItemCallback<Cake>() {
        override fun areItemsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Cake, newItem: Cake): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val cake = getItem(position) ?:return



        holder.apply {
            Glide.with(itemView).setDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA))
                .load(cake.img).into(ivCake)

            itemView.setOnClickListener {
             //   val directions= HomeFragmentDirections.actionHomeFragmentToDetailFragment(cake)
             //   it.findNavController().navigate(directions)
            }

            tvTitle.text = cake.title
            val sizePice = "Ksh: ${cake.price} "
            tvPrice.text = sizePice


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
}







