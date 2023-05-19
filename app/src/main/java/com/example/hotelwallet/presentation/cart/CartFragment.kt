package com.example.hotelwallet.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentCartBinding
import com.example.hotelwallet.domain.model.SubMenu
import com.example.hotelwallet.domain.model.ToolbarConfiguration
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(
    FragmentCartBinding::inflate,
    toolbarConfiguration = ToolbarConfiguration(
        visibility = View.VISIBLE,
        btnBackVisibility = View.GONE,
        title = R.string.txt_title_cart
    )
) {

    private val cartViewModel by activityViewModels<CartViewModel>()

    private lateinit var cartAdapter: CartAdapter
    private var productList = mutableListOf<SubMenu>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(productList) {
        }

        binding.recyclerViewCart.setHasFixedSize(true)
        binding.recyclerViewCart.isNestedScrollingEnabled = false
        binding.recyclerViewCart.adapter = cartAdapter

        cartViewModel.getProductList()
        observeCart()

        setBottomNavigation(true)
    }

    private fun observeCart() {
        lifecycleScope.launchWhenStarted {
            cartViewModel.stateProductList.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> setLoading(true)
                    is Resource.Success -> {
                        it.data.apply {
                            productList.clear()
                            productList.addAll(this)
                            cartAdapter.notifyDataSetChanged()
                            binding.txtNoItems.isVisible = this.isEmpty()

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