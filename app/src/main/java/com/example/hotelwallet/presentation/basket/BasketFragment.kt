package com.example.hotelwallet.presentation.basket

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.R
import com.example.hotelwallet.data.source.local.Basket
import com.example.hotelwallet.databinding.FragmentBasketBinding
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource
import com.google.android.material.snackbar.Snackbar

class BasketFragment : BaseFragment<FragmentBasketBinding>(
    FragmentBasketBinding::inflate
) {

    private lateinit var detailMenuAdapter: BasketAdapter
    private val basketViewModel by activityViewModels<BasketViewModel>()
    private var detailList = mutableListOf<Basket>()
    private val commandeViewModel by activityViewModels<CommandeViewModel>()
    private var totalPrice = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCustomToolbar("name", "solde", false, false)

        basketViewModel.allFavorites
        detailMenuAdapter = BasketAdapter(emptyList(), basketViewModel)
        binding.recyclerViewSaved.setHasFixedSize(true)
        binding.recyclerViewSaved.isNestedScrollingEnabled = false
        binding.recyclerViewSaved.adapter = detailMenuAdapter
        binding.recyclerViewSaved.layoutManager = LinearLayoutManager(requireContext())

        basketViewModel.allFavorites.observe(viewLifecycleOwner) { basket ->
            detailMenuAdapter = BasketAdapter(basket, basketViewModel)
            binding.recyclerViewSaved.adapter = detailMenuAdapter
            val totalPrice = calculateTotalPrice(basket)
            binding.tvTotalAmount.text = "$totalPrice"
        }

        binding.btnPay.setOnClickListener{
            val message: String? = "Are you sure "
            showCustomDialogBox(message)
        }

    }


    private fun observeCommande(){
        lifecycleScope.launchWhenStarted {
            commandeViewModel.stateCommande.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        basketViewModel.deleteAll()
//                        Snackbar.make(view, it.data.message, Snackbar.LENGTH_LONG).show()
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG).show()

                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            " it.data.message",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }
    }
    private fun ajouterCommande(
        id_client: Int,
        prix_total: Int
    ) {

        commandeViewModel.getCommande(id_client, prix_total)
    }

    fun calculateTotalPrice(menuItems: List<Basket>): Int {

        for (menuItem in menuItems) {
            totalPrice += menuItem.price.toInt() * menuItem.quantity.toInt()
        }
        return totalPrice
    }

    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dailog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage: TextView = dialog.findViewById(R.id.txtMessage)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        tvMessage.text = message

        btnYes.setOnClickListener {
            observeCommande()
            ajouterCommande(4,totalPrice)
            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}

