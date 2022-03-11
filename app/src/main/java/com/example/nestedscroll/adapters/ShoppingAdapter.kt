package com.kevin.cakeCity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nestedscroll.R
import com.example.nestedscroll.data.Item
import com.example.nestedscroll.databinding.ItemShoppingBinding


class ShoppingAdapter :RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {
    private lateinit var binding: ItemShoppingBinding
     class ShoppingViewHolder(binding: ItemShoppingBinding) : RecyclerView.ViewHolder(binding.root){
        val tvName= binding.tvName
         val tvPrice = binding.tvPrice
            val image = binding.ivCake
     }

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var shoppingItems: List<Item>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            ItemShoppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val shoppingItem = shoppingItems[position]
       holder.apply {
           Glide.with(itemView).setDefaultRequestOptions(RequestOptions()
               .placeholder(R.drawable.p)
               .error(R.drawable.ic_broken_image)
               .diskCacheStrategy(DiskCacheStrategy.DATA))
               .load(shoppingItem.img).into(image)
        tvName.text = shoppingItem.title

           val priceText = "${shoppingItem.price}Ksh"
        tvPrice.text = priceText
       }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

}