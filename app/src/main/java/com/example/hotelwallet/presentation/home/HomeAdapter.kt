package com.example.hotelwallet.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.databinding.RowItemCategoryBinding
import com.example.hotelwallet.domain.model.Services


class HomeAdapter(
    private var menuList: List<Services>,
    private val listener: (Services) -> Unit
) : RecyclerView.Adapter<HomeAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = RowItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            with(menuList[position]) {
                binding.txtCategoryName.text = nom
                Glide.with(itemView)
                    .load(image)
                    .into(binding.imgCategory)

                itemView.setOnClickListener {
                    listener(this)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setFilteredList(menuList: List<Services>) {
        this.menuList = menuList
        notifyDataSetChanged()
    }

    inner class MenuViewHolder(val binding: RowItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


}




