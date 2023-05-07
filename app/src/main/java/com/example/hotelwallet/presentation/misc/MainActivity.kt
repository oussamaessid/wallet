package com.example.hotelwallet.presentation.misc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.ActivityMainBinding
import com.example.hotelwallet.utility.TAG_ALERT_DIALOG_ERROR
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

    }

    fun setCustomToolbar(name:String , solde:String ,isCustomToolbar: Boolean , imgArrow : Boolean){
        if (isCustomToolbar) {
                binding.customToolbar.root.visibility = View.VISIBLE
                binding.customToolbar.txtTitleName.text = name
                binding.customToolbar.txtAmount.text = solde
            if (imgArrow) {
                binding.customToolbar.imgArrow.visibility = View.VISIBLE
                binding.customToolbar.imgArrow.setOnClickListener {
                    onBackPressed()
                }
            }else
                binding.customToolbar.imgArrow.visibility = View.INVISIBLE
        }
        else {
            binding.customToolbar.root.visibility = View.GONE
        }
    }
    fun setBottomNavigation(isNavigation: Boolean ){
        if (isNavigation) {
            binding.bottomNavContent.visibility = View.VISIBLE
            val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navController = Navigation.findNavController(this, R.id.mainNavHostContainer)

            NavigationUI.setupWithNavController(bottomNavigation, navController)
        }
        else {
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

    fun setErrorAlert(
        show: Boolean,
        errorMsg: String?,
        titleMsg: Any? = null,
        recreateActivity: Boolean = false,
        finishActivity: Boolean = false
    ) {
        if (show) {
            this.supportFragmentManager.let { fragmentManager ->
                AlertDialogFragment().apply {
                    titleId = titleMsg
                    descriptionId = errorMsg
                    positiveClickListener = {
                        dismiss()
                        when {
                            finishActivity -> finish()
                            recreateActivity -> recreate()
                        }
                    }
                }.show(fragmentManager, TAG_ALERT_DIALOG_ERROR)
            }
        }
    }
}