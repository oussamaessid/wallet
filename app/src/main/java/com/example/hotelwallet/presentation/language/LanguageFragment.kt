package com.example.hotelwallet.presentation.language

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentLanguageBinding
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.KEY_Arabic
import com.example.hotelwallet.utility.KEY_ENGLISH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(
    FragmentLanguageBinding::inflate
), View.OnClickListener {

    private val languageViewModel by viewModels<LanguageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            languageViewModel.stateLanguage.collect { language ->
                when (language) {
                    KEY_ENGLISH -> {
                        binding.imgCheckedEn.setImageResource(R.drawable.circle_punch)
                        binding.imgCheckedDe.setImageResource(R.drawable.circle_grey)
                    }
                    KEY_Arabic -> {
                        binding.imgCheckedEn.setImageResource(R.drawable.circle_grey)
                        binding.imgCheckedDe.setImageResource(R.drawable.circle_punch)
                    }
                }
            }
        }

        binding.linearLanguageEnglish.setOnClickListener(this)
        binding.linearLanguageGermany.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.linear_language_english -> {

            }
            R.id.linear_language_germany -> {
            }
        }
    }
}