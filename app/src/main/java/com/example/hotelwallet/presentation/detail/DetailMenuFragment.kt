package com.example.hotelwallet.presentation.detail

import android.os.Bundle
import android.view.View
import coil.load
import com.bumptech.glide.Glide
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentDetailMenuBinding
import com.example.hotelwallet.presentation.misc.BaseFragment


class DetailMenuFragment : BaseFragment<FragmentDetailMenuBinding>(
    FragmentDetailMenuBinding::inflate
), View.OnClickListener {

    private lateinit var strMeal: String
    private lateinit var strMealThumb: String
    var totalInCart: Int = 1
    var price: Int = 3000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtCount.text = totalInCart.toString()
        binding.txtPrice.text = price.toString()
        binding.imageMinus.setOnClickListener(this)
        binding.imageAdd.setOnClickListener(this)
        setBottomNavigation(true)

    }

//    private fun getMealInformationFromIntent() {
//        val args = this.arguments
//        strMeal = args?.get("strMeal").toString()
//        strMealThumb = args?.get("strMealThumb").toString()
//    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageMinus -> {
                totalInCart--
                if (totalInCart > 0) {
                    binding.txtCount.text = totalInCart.toString()
                      val total = price * totalInCart
                      binding.txtPrice.text = total.toString()
                }
            }
            R.id.imageAdd -> {
                totalInCart++
                if (totalInCart <= 10) {
                       binding.txtCount.text = totalInCart.toString()
                       val total = price * totalInCart
                       binding.txtPrice.text = total.toString()
                }
            }
        }

    }
}