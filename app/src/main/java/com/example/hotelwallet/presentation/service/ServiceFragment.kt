package com.example.hotelwallet.presentation.service

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentServiceBinding
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.presentation.home.HomeAdapter
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.CATEGORY_EAT


class ServiceFragment : BaseFragment<FragmentServiceBinding>(
    FragmentServiceBinding::inflate
) {

    private lateinit var homeAdapter: HomeAdapter
    private var menuList = mutableListOf<Services>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeAdapter = HomeAdapter(menuList) { type ->
            if (type.id == CATEGORY_EAT) {
                findNavController().navigate(R.id.action_serviceFragment_to_menuFragment)
            } else {
                findNavController().navigate(R.id.action_serviceFragment_to_menuFragment)

            }
        }
        binding.recyclerViewService.setHasFixedSize(true)
        binding.recyclerViewService.isNestedScrollingEnabled = false
        binding.recyclerViewService.adapter = homeAdapter
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