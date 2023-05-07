package com.example.hotelwallet.presentation.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.databinding.RowItemDetailBinding
import dagger.hilt.android.lifecycle.HiltViewModel

class BasketAdapter(
    private val menuList: List<Basket>,
    private val viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketAdapter.MenuViewHolder>() {

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
                    viewModel.deleteFavorite(menuList[position])
                }

                binding.imageAdd.setOnClickListener {
                    val count = binding.txtCount.text.toString()
                    val quant = count.toInt() + 1
                    binding.txtCount.text = quant.toString()
                    val prix = price.toInt() * quant
                    binding.menuPrice.text =  prix.toString()
                }
                binding.imageMinus.setOnClickListener {
                    val count = binding.txtCount.text.toString()
                    val quanti = count.toInt() - 1
                    binding.txtCount.text = quanti.toString()
                    val menupric = binding.menuPrice.text.toString()
                    val prixx = menupric.toInt() - (price.toInt() * quantity.toInt() )
                    binding.menuPrice.text =  prixx.toString()
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




