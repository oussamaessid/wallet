package com.example.hotelwallet.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentDetailBinding
import com.example.hotelwallet.domain.model.MenuItem
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource


class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {

    private val detailViewModel by activityViewModels<DetailViewModel>()

    private lateinit var detailListAdapter: DetailListAdapter
    private var detailList = mutableListOf<MenuItem>()
    private lateinit var categoryName: String
    private lateinit var categoryDescription: String
    private lateinit var idcategory: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailListAdapter = DetailListAdapter(detailList) {
            val bundle = Bundle()
            bundle.putString("id", it.id.toString())
            bundle.putString("nom", it.nom)
            bundle.putString("image", it.image)
            bundle.putString("description", it.description)
            bundle.putString("prix", it.prix)
            findNavController().navigate(R.id.action_detailFragment_to_detailMenuFragment,bundle)
        }

        setBottomNavigation(true)
        val args = this.arguments
        categoryName = args?.get("category_name").toString()
        categoryDescription = args?.get("category_description").toString()
        idcategory = args?.get("id_category").toString()

        binding.recyclerViewMenu.setHasFixedSize(true)
        binding.recyclerViewMenu.isNestedScrollingEnabled = false
        binding.recyclerViewMenu.adapter = detailListAdapter
        detailViewModel.getFavorite(idcategory)
        observeFavorites()

    }

    private fun observeFavorites() {
        lifecycleScope.launchWhenStarted {
            detailViewModel.stateFavorite.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        setLoading(true)
                    }
                    is Resource.Success -> {
                        it.data.apply {
                            detailList.clear()
                            detailList.addAll(this)
                            detailListAdapter.notifyDataSetChanged()

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


//    private fun insertDataToDatabase() {
//        val name  = menuItem.strMeal
//        val image = menuItem.strMealThumb
//        val payItem = PayItem(0,name,image)
//        basketViewModel.addUser(payItem)
//    }
}