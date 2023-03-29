package com.example.hotelwallet.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentWelcomeBinding
import com.example.hotelwallet.domain.model.WelcomeSlide
import com.example.hotelwallet.presentation.misc.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
), View.OnClickListener {

    private lateinit var slideAdapter: WelcomeOnBoardingAdapter
    private var slideList = mutableListOf<WelcomeSlide>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavigation(false)
        binding.btnEnglish.setOnClickListener(this)
        binding.btnArabic.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_english -> {
                findNavController().navigate(R.id.action_welcomeFragment_to_welcomeOnBoardingFragment)
            }
            R.id.btn_arabic -> {
                findNavController().navigate(R.id.action_welcomeFragment_to_welcomeOnBoardingFragment)
            }
        }
    }
}