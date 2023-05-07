package com.example.hotelwallet.presentation.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.databinding.FragmentFavoriteBinding
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.presentation.basket.BasketAdapter
import com.example.hotelwallet.presentation.basket.BasketViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteViewModel: FavoriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCustomToolbar("name","solde",false,false)

        favoriteViewModel.getallFavorites
        favoriteAdapter = FavoriteAdapter(emptyList(),favoriteViewModel)
        binding.recyclerViewFavorite.setHasFixedSize(true)
        binding.recyclerViewFavorite.isNestedScrollingEnabled = false
        binding.recyclerViewFavorite.adapter = favoriteAdapter
        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())

        favoriteViewModel.getallFavorites.observe(viewLifecycleOwner) { favorite ->
            favoriteAdapter = FavoriteAdapter(favorite,favoriteViewModel)
            binding.recyclerViewFavorite.adapter = favoriteAdapter

        }
    }

}