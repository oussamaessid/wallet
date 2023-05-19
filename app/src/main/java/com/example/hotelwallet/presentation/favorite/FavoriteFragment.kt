package com.example.hotelwallet.presentation.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.R
import com.example.hotelwallet.data.source.local.Favorite
import com.example.hotelwallet.databinding.FragmentFavoriteBinding
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.domain.model.ToolbarConfiguration
import com.example.hotelwallet.presentation.misc.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate,
    toolbarConfiguration = ToolbarConfiguration(
        visibility = View.VISIBLE,
        btnBackVisibility = View.VISIBLE,
        title = R.string.txt_title_service
    )
) {

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        basketViewModel.getallFavorites
//        favoriteAdapter = FavoriteAdapter(emptyList(),basketViewModel)
//        binding.recyclerViewFavorite.setHasFixedSize(true)
//        binding.recyclerViewFavorite.isNestedScrollingEnabled = false
//        binding.recyclerViewFavorite.adapter = favoriteAdapter
//        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())
//
//        basketViewModel.getallFavorites.observe(viewLifecycleOwner) { favorite ->
//            favoriteAdapter = FavoriteAdapter(favorite,basketViewModel)
//            binding.recyclerViewFavorite.adapter = favoriteAdapter
//
//        }
    }

}