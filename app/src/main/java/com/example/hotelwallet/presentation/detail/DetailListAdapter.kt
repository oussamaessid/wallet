package com.example.hotelwallet.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.databinding.RowItemCategoryBinding
import com.example.hotelwallet.domain.model.MenuItem

class DetailListAdapter(
    private val favoriteList: List<MenuItem>,
    private val listener: (MenuItem) -> Unit
) : RecyclerView.Adapter<DetailListAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = RowItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        with(holder) {
            with(favoriteList[position]) {
                binding.txtCategoryName.text = strMeal
                Glide.with(itemView)
                    .load(strMealThumb)
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