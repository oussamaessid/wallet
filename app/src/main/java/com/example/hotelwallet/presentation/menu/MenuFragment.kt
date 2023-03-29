package com.example.hotelwallet.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentMenuBinding
import com.example.hotelwallet.domain.model.Category
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(
    FragmentMenuBinding::inflate
) {

    private val favoriteViewModel by activityViewModels<MenuViewModel>()

    private lateinit var menuListAdapter: MenuListAdapter
    private var menuList = mutableListOf<Category>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuListAdapter = MenuListAdapter(menuList) {
            val bundle = Bundle()
            bundle.putString("category_name", it.strCategory)
            bundle.putString("category_description", it.strCategoryDescription)
            bundle.putString("category_thumb", it.strCategoryThumb)
            findNavController().navigate(R.id.action_menuFragment_to_detailFragment)
        }

        binding.customToolbar.imgArrow.isVisible = true
        binding.customToolbar.imgArrow.setOnClickListener {
        }
        binding.recyclerViewCategories.setHasFixedSize(true)
        binding.recyclerViewCategories.isNestedScrollingEnabled = false
        binding.recyclerViewCategories.adapter = menuListAdapter

        favoriteViewModel.getCategories()
        observeFavorites()

        setBottomNavigation(true)
    }

    private fun observeFavorites() {
        lifecycleScope.launchWhenStarted {
            favoriteViewModel.stateCategories.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        setLoading(true)

                    }
                    is Resource.Success -> {
                        it.data.apply {
                            menuList.clear()
                            menuList.addAll(this)
                            menuListAdapter.notifyDataSetChanged()

                        }
                        setLoading(false)
                    }
                    is Resource.Error -> {
                        setLoading(false)
                    }
                }
            }
        }
    }


}