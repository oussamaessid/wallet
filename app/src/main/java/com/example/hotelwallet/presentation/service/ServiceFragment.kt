package com.example.hotelwallet.presentation.service

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentServiceBinding
import com.example.hotelwallet.domain.model.Services
import com.example.hotelwallet.presentation.home.HomeAdapter
import com.example.hotelwallet.presentation.home.HomeViewModel
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.CATEGORY_EAT
import com.example.hotelwallet.utility.Resource


class ServiceFragment : BaseFragment<FragmentServiceBinding>(
    FragmentServiceBinding::inflate
) {

    private lateinit var serviceAdapter: ServiceAdapter
    private var menuList = mutableListOf<Services>()
    private val serviceViewModel by activityViewModels<ServiceViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceAdapter = ServiceAdapter(menuList) { type ->
            val bundle = Bundle()
            bundle.putString("id_service", type.id_service.toString())
            Log.println(Log.ASSERT, "if_category1", type.id_service.toString())
            if (type.type.toInt() == CATEGORY_EAT) {
                findNavController().navigate(R.id.action_serviceFragment_to_menuFragment, bundle)
            } else {
                findNavController().navigate(R.id.action_serviceFragment_to_gymFragment, bundle)
            }
        }
        binding.recyclerViewService.setHasFixedSize(true)
        binding.recyclerViewService.isNestedScrollingEnabled = false
        binding.recyclerViewService.adapter = serviceAdapter
        serviceViewModel.getServices()
        observeServices()
    }

    private fun observeServices() {
        lifecycleScope.launchWhenStarted {
            serviceViewModel.stateServices.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        setLoading(true)
                    }
                    is Resource.Success -> {
                        it.data.apply {
                            menuList.clear()
                            menuList.addAll(this)
                            serviceAdapter.notifyDataSetChanged()

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

}