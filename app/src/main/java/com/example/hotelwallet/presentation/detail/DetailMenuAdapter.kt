package com.example.hotelwallet.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.databinding.RowItemDetailBinding
import com.example.hotelwallet.domain.model.Details


class DetailMenuAdapter(
    private val menuList: List<Basket>
) : RecyclerView.Adapter<DetailMenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = RowItemDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            with(menuList[position]) {
                binding.tvName.text = name
                binding.tvAmount.text = price
            }

        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    inner class MenuViewHolder(val binding: RowItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root)


}




