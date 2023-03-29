package com.example.hotelwallet.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentHomeBinding
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.CATEGORY_EAT
import com.example.hotelwallet.utility.Resource
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var homeAdapter: HomeAdapter
    private var menuList = mutableListOf<Services>()
    val imageList = ArrayList<SlideModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavigation(true)
        homeAdapter = HomeAdapter(menuList) { id ->
            if (id.id == CATEGORY_EAT) {
                findNavController().navigate(R.id.action_homeFragment_to_menuFragment)
            } else {
                findNavController().navigate(R.id.action_homeFragment_to_gymFragment)
            }
        }
        binding.customToolbar.imgNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }


        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.isNestedScrollingEnabled = false
        binding.recyclerViewHome.adapter = homeAdapter
        homeViewModel.getServices()
        observeServices()

        imageList.clear()
        imageList.add(SlideModel(R.drawable.img_hotel1, "Amir Palace"))
        imageList.add(SlideModel(R.drawable.img_hotel2, "Amir Palace"))
        imageList.add(SlideModel(R.drawable.img_hotel3, "Amir Palace"))
        imageList.add(SlideModel(R.drawable.img_hotel4, "Amir Palace"))
        imageList.add(SlideModel(R.drawable.img_hotel5, "Amir Palace"))

        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                filterList(query)
                val bundle = Bundle()
                bundle.putString("query", query)
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                filterList(newText)
                return false
            }
        })

    }

    private fun observeServices() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.stateServices.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        setLoading(true)
                    }
                    is Resource.Success -> {
                        it.data.apply {
                            menuList.clear()
                            menuList.addAll(this)
                            homeAdapter.notifyDataSetChanged()

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


    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Services>()
            for (i in menuList) {
                if (i.nom.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                homeAdapter.setFilteredList(filteredList)
            }
        }
    }

//    private fun setSlideList() {
//        menuList.add(
//            Service(
//                R.drawable.img_pizza,
//                "Restaurant",
//                1
//            )
//        )
//        menuList.add(
//            Service(
//                R.drawable.img_pizza,
//                "Salle De Sport",
//                2
//            )
//        )
//        menuList.add(
//            Service(
//                R.drawable.img_pizza,
//                "Restaurant",
//                1
//            )
//        )
//        menuList.add(
//            Service(
//                R.drawable.img_pizza,
//                "Salle De Sport ",
//                2
//            )
//        )
//        menuList.add(
//            Service(
//                R.drawable.img_pizza,
//                "Salle De Sport ",
//                2
//            )
//        )
//    }


}