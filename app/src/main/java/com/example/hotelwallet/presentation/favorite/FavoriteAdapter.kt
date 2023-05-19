package com.example.hotelwallet.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.databinding.RowItemDetailBinding


class FavoriteAdapter(
    private val menuList: List<Favorite>,
) : RecyclerView.Adapter<FavoriteAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = RowItemDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            with(menuList[position]) {
                binding.menuName.text = name
                binding.menuPrice.text = price
                binding.txtCount.text = quantity
                Glide.with(itemView)
                    .load(image)
                    .into(binding.thumbImage)

                binding.ivDelete.setOnClickListener {
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    inner class MenuViewHolder(val binding: RowItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root)


}




