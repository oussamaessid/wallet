package com.example.hotelwallet.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.databinding.RowItemCategoryBinding
import com.example.hotelwallet.domain.model.Category

class MenuListAdapter(
    private val favoriteList: List<Category>,
    private val listener: (Category) -> Unit
) : RecyclerView.Adapter<MenuListAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = RowItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(holder) {
            with(favoriteList[position]) {
                binding.txtCategoryName.text = nom
                Glide.with(itemView)
                    .load(description)
                    .into(binding.imgCategory)

                itemView.setOnClickListener {
                    listener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class FavoriteViewHolder(val binding: RowItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

}