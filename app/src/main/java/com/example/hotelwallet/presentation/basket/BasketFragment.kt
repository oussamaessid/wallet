package com.example.hotelwallet.presentation.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.databinding.FragmentBasketBinding
import com.example.hotelwallet.presentation.detail.DetailMenuAdapter
import com.example.hotelwallet.presentation.misc.BaseFragment

class BasketFragment : BaseFragment<FragmentBasketBinding>(
    FragmentBasketBinding::inflate
) {

    private lateinit var detailMenuAdapter: DetailMenuAdapter
    private val basketViewModel: BasketViewModel by viewModels()
    private var detailList = mutableListOf<Basket>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        basketViewModel.allFavorites
        detailMenuAdapter = DetailMenuAdapter(emptyList())
        binding.recyclerViewSaved.setHasFixedSize(true)
        binding.recyclerViewSaved.isNestedScrollingEnabled = false
        binding.recyclerViewSaved.adapter = detailMenuAdapter
        binding.recyclerViewSaved.layoutManager = LinearLayoutManager(requireContext())

        basketViewModel.allFavorites.observe(viewLifecycleOwner) { basket ->
            detailMenuAdapter = DetailMenuAdapter(basket)
            binding.recyclerViewSaved.adapter = detailMenuAdapter
        }

//        basketViewModel.getAllShoppingItems().observe(requireActivity(), Observer {
//            detailMenuAdapter.menuList = it
//            detailMenuAdapter.notifyDataSetChanged()
//        })
    }
}
