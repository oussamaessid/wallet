package com.example.hotelwallet.presentation.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentWelcomeOnBoardingBinding
import com.example.hotelwallet.domain.model.WelcomeSlide
import com.example.hotelwallet.presentation.language.LanguageViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.KEY_ENGLISH
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class WelcomeOnBoardingFragment : BaseFragment<FragmentWelcomeOnBoardingBinding>(
    FragmentWelcomeOnBoardingBinding::inflate
), View.OnClickListener {

    private lateinit var slideAdapter: WelcomeOnBoardingAdapter
    private var slideList = mutableListOf<WelcomeSlide>()
    private val languageViewModel by viewModels<LanguageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSlideList()
        setBottomNavigation(false)
        binding.btnNext.setOnClickListener(this)
        binding.btnSkip.setOnClickListener(this)
    }

    private fun setSlideList() {
        slideList.add(
            WelcomeSlide(
                R.drawable.bacground4,
                getString(R.string.title_onboarding_1),
                getString(R.string.description_onboarding_1)
            )
        )
        slideList.add(
            WelcomeSlide(
                R.drawable.bacground4,
                getString(R.string.title_onboarding_2),
                getString(R.string.description_onboarding_2)
            )
        )
        slideList.add(
            WelcomeSlide(
                R.drawable.bacground4,
                getString(R.string.title_onboarding_3),
                getString(R.string.description_onboarding_3)
            )
        )

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == slideList.size - 1) {
                    binding.onBoardingMotion.transitionToEnd()
                } else {
                    binding.onBoardingMotion.transitionToStart()
                }
            }

        })
        slideAdapter = WelcomeOnBoardingAdapter(slideList)
        binding.viewPager.adapter = slideAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnNext -> {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            }
            R.id.btnSkip -> {
                findNavController().navigate(R.id.action_welcomeOnBoardingFragment_to_scannerFragment)
            }
        }
    }
}