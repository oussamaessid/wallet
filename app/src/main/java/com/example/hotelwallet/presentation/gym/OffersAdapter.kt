package com.example.hotelwallet.presentation.gym

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelwallet.databinding.RowItemOfferBinding
import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.domain.model.WelcomeSlide

class OffersAdapter (
    private val popularList: List<Gym>,
    private val listener: (Gym) -> Unit
) : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = RowItemOfferBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        with(holder) {
            with(popularList[position]) {
                Glide.with(itemView)
                    .load(image)
                    .into(binding.imgOrder)
                binding.txtName.text = description
                binding.txtUserName.text = nom
                binding.txtPrice.text = prix

                binding.btnAdd.setOnClickListener{
                    listener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    inner class OfferViewHolder(val binding: RowItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root)

}