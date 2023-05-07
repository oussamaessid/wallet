package com.example.hotelwallet.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hotelwallet.R
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.databinding.FragmentDetailMenuBinding
import com.example.hotelwallet.presentation.basket.BasketViewModel
import com.example.hotelwallet.presentation.favorite.FavoriteViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.google.android.material.snackbar.Snackbar

class DetailMenuFragment : BaseFragment<FragmentDetailMenuBinding>(
    FragmentDetailMenuBinding::inflate
), View.OnClickListener {

    private lateinit var Id: String
    private lateinit var Name: String
    private lateinit var Description: String
    private lateinit var Image: String
    private lateinit var Prix: String
    var totalInCart: Int = 1
    private val basketViewModel by activityViewModels<BasketViewModel>()
    private val favoriteViewModel: FavoriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCustomToolbar("name","solde",false,false)


        val args = this.arguments
        Id = args?.get("id").toString()
        Name = args?.get("nom").toString()
        Description = args?.get("description").toString()
        Image = args?.get("image").toString()
        Prix = args?.get("prix").toString()
        Log.println(Log.ASSERT, "id", Id)
        binding.txtCount.text =totalInCart.toString()
        binding.txtPrice.text = Prix
        binding.txtDescription.text = Description
        setInformationInView()
        binding.imageMinus.setOnClickListener(this)
        binding.imageAdd.setOnClickListener(this)
        setBottomNavigation(true)

        setFloatingButtonStatues()

        binding.btnReservation.setOnClickListener {
            val prix = binding.txtPrice.text.toString()
            val counts = binding.txtCount.text.toString()
            val favorite = Basket(Name,prix, counts, Image)
            basketViewModel.insertFavorite(favorite)
        }

        binding.btnBasket.setOnClickListener {
            findNavController().navigate(R.id.action_detailMenuFragment_to_basketFragment)
        }

        binding.btnSave.setOnClickListener {
            if(isMealSavedInDatabase()){
                deleteMeal()
                binding.btnSave.setImageResource(R.drawable.ic_fav)
                Snackbar.make(view, "Meal was deleted", Snackbar.LENGTH_LONG).show()

            }else{
                val prix = binding.txtPrice.text.toString()
                val counts = binding.txtCount.text.toString()
                val favorite = Favorite(Name,prix, counts, Image)
                favoriteViewModel.addFavorite(favorite)
                binding.btnSave.setImageResource(R.drawable.img_saved)
                Snackbar.make(view, "Meal saved", Snackbar.LENGTH_LONG).show()

            }
        }
    }

    private fun setFloatingButtonStatues() {
        if(isMealSavedInDatabase()){
            binding.btnSave.setImageResource(R.drawable.img_saved)
        }else{
            binding.btnSave.setImageResource(R.drawable.ic_fav)
        }
    }
    private fun isMealSavedInDatabase(): Boolean {
        return favoriteViewModel.isMealSavedInDatabase(Name)
    }

//    private fun saveMeal() {
//        val prix = binding.txtPrice.text.toString()
//        val counts = binding.txtCount.text.toString()
//        val favorite = Basket(Name,prix, counts, Image)
//        BasketViewModel.insertFavorite(favorite)
//    }
    private fun setInformationInView() {
        Glide.with(requireContext())
            .load(Image)
            .into(binding.imgDetail)

        binding.collapsingToolbar.title = Name
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorWhite))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.colorWhite))
    }

    private fun deleteMeal() {
        favoriteViewModel.deleteMealById(Name)
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