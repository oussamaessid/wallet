package com.example.hotelwallet.presentation.gym

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentGymBinding
import com.example.hotelwallet.domain.model.SalleDeSport
import com.example.hotelwallet.presentation.misc.BaseFragment


class GymFragment : BaseFragment<FragmentGymBinding>(
    FragmentGymBinding::inflate
) {

    private var menuList = mutableListOf<SalleDeSport>()
    private lateinit var gymAdapter: OffersAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentGym, OffersFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
        onClikGym()
        binding.customToolbar.imgArrow.isVisible = true
        setBottomNavigation(true)
    }

    private fun onClikGym() {
        binding.btnOffers.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentGym, OffersFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        binding.btnCoach.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentGym, CoachFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        binding.btnCours.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentGym, CoursFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }


}

