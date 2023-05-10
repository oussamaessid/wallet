package com.example.hotelwallet.presentation.misc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.ActivityMainBinding
import com.example.hotelwallet.utility.TAG_ALERT_DIALOG_ERROR
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : LocaleAwareCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val appViewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    fun setBottomNavigation(isNavigation: Boolean) {
        if (isNavigation) {
            binding.bottomNavContent.visibility = View.VISIBLE
            val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navController = Navigation.findNavController(this, R.id.mainNavHostContainer)

            NavigationUI.setupWithNavController(bottomNavigation, navController)
        } else {
            binding.bottomNavContent.visibility = View.GONE

        }
    }

    fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.root.visibility = View.VISIBLE
            binding.loadingView.animationLoading.playAnimation()
        } else {
            binding.loadingView.root.visibility = View.GONE
            binding.loadingView.animationLoading.cancelAnimation()
        }
    }

    override fun updateLocale(locale: Locale) {
        super.updateLocale(locale)
    }

    fun setErrorAlert(
        errorMsg: String?,
        titleMsg: Any? = null,
        positiveBtn: Int,
        negativeBtn: Int? = null,
        positiveClick: ((View) -> Unit)?= null,
        negativeClick: ((View) -> Unit)? = null
    ) {
        this.supportFragmentManager.let { fragmentManager ->
            AlertDialogFragment().apply {
                titleId = titleMsg
                descriptionId = errorMsg
                positiveBtnName = positiveBtn
                negativeBtnName = negativeBtn
                positiveClickListener = positiveClick
                negativeClickListener = negativeClick
            }.show(fragmentManager, TAG_ALERT_DIALOG_ERROR)
        }
    }
}