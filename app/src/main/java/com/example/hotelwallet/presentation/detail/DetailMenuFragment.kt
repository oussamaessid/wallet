package com.example.hotelwallet.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hotelwallet.R
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.databinding.FragmentDetailMenuBinding
import com.example.hotelwallet.presentation.basket.BasketViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment

class DetailMenuFragment : BaseFragment<FragmentDetailMenuBinding>(
    FragmentDetailMenuBinding::inflate
), View.OnClickListener {

    private lateinit var Id: String
    private lateinit var Name: String
    private lateinit var Description: String
    private lateinit var Image: String
    private lateinit var Prix: String
    var totalInCart: Int = 1
    private val detailMenuViewModel by activityViewModels<BasketViewModel>()
    private val basketViewModel: BasketViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        Id = args?.get("nom").toString()
        Name = args?.get("nom").toString()
        Description = args?.get("description").toString()
        Image = args?.get("image").toString()
        Prix = args?.get("prix").toString()
        var count = binding.txtCount.text
        count = totalInCart.toString()
        binding.txtPrice.text = Prix
        binding.txtDescription.text = Description
        setInformationInView()
        binding.imageMinus.setOnClickListener(this)
        binding.imageAdd.setOnClickListener(this)
        setBottomNavigation(true)

        binding.btnReservation.setOnClickListener {
            val favorite = Basket(Name, Prix, count, Image)
            basketViewModel.insertFavorite(favorite)
        }

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_detailMenuFragment_to_basketFragment)
        }
    }

    private fun setInformationInView() {
        Glide.with(requireContext())
            .load(Image)
            .into(binding.imgDetail)

        binding.collapsingToolbar.title = Name
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorWhite))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.colorWhite))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageMinus -> {
                totalInCart--
                if (totalInCart > 0) {
                    binding.txtCount.text = totalInCart.toString()
                    val total = Prix.toInt() * totalInCart
                    binding.txtPrice.text = total.toString()
                }
            }
            R.id.imageAdd -> {
                totalInCart++
                if (totalInCart <= 10) {
                    binding.txtCount.text = totalInCart.toString()
                    val total = Prix.toInt() * totalInCart
                    binding.txtPrice.text = total.toString()
                }
            }
        }

    }
}