package com.example.hotelwallet.presentation.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be call")
        }
        return binding.root
    }

    fun setCustomToolbar(name:String , solde:String ,isCustomToolbar: Boolean,imgArrow: Boolean){
        (activity as MainActivity).setCustomToolbar(name,solde,isCustomToolbar,imgArrow)

    }
    fun setLoading(loading: Boolean) {
        (activity as MainActivity).setLoading(loading)
    }

    fun setBottomNavigation(navigation: Boolean) {
        (activity as MainActivity).setBottomNavigation(navigation)
    }

    fun setErrorAlert(show: Boolean, errorMsg: String?, titleMsg: Any? = null) {
        (activity as MainActivity).setErrorAlert(show, errorMsg, titleMsg)
    }

    fun onBack() {
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}