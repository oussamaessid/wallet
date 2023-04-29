package com.example.hotelwallet.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentWelcomeBinding
import com.example.hotelwallet.domain.model.WelcomeSlide
import com.example.hotelwallet.presentation.language.LanguageViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.KEY_Arabic
import com.example.hotelwallet.utility.KEY_ENGLISH
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
), View.OnClickListener {

    private val languageViewModel by viewModels<LanguageViewModel>()
    private lateinit var slideAdapter: WelcomeOnBoardingAdapter
    private var slideList = mutableListOf<WelcomeSlide>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavigation(false)
        binding.btnEnglish.setOnClickListener(this)
        binding.btnArabic.setOnClickListener(this)
    }


    private fun setLocal(language: String) {
        val metrics = resources.displayMetrics
        val configuration = resources.configuration
        lifecycleScope.launch {
            languageViewModel.saveLanguage(language)
            configuration.setLocale(Locale(language))
            resources.updateConfiguration(configuration, metrics)
            onConfigurationChanged(configuration)
        }
    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_english -> {
                setLocal(KEY_ENGLISH)
                findNavController().navigate(R.id.action_welcomeFragment_to_welcomeOnBoardingFragment)
            }
            R.id.btn_arabic -> {
                setLocal(KEY_Arabic)
                findNavController().navigate(R.id.action_welcomeFragment_to_welcomeOnBoardingFragment)
            }
        }
    }
}