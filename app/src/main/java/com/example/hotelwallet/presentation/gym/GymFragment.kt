package com.example.hotelwallet.presentation.gym

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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentGymBinding
import com.example.hotelwallet.domain.model.Gym
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource


class GymFragment : BaseFragment<FragmentGymBinding>(
    FragmentGymBinding::inflate
) {

    private var menuList = mutableListOf<Gym>()
    private lateinit var gymAdapter: OffersAdapter
    private val gymViewModel by activityViewModels<GymViewModel>()
    private lateinit var idcategory: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavigation(true)

        gymAdapter = OffersAdapter(menuList){
            val message: String? = "Are you sure "
            showCustomDialogBox(message)
        }

        getMealInformationFromIntent()
        binding.recyclerViewOffers.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOffers.setHasFixedSize(true)
        binding.recyclerViewOffers.isNestedScrollingEnabled = false
        binding.recyclerViewOffers.adapter = gymAdapter

        gymViewModel.getCategories(idcategory)
        observeFavorites()
    }

    private fun observeFavorites() {
        lifecycleScope.launchWhenStarted {
            gymViewModel.stateCategories.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        setLoading(true)

                    }
                    is Resource.Success -> {
                        it.data.apply {
                            menuList.clear()
                            menuList.addAll(this)
                            gymAdapter.notifyDataSetChanged()

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

    private fun observeAchat(){
        lifecycleScope.launchWhenStarted {
            gymViewModel.stateAchat.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
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
            observeAchat()
            ajouterAchat(4,1)
            dialog.dismiss()        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun ajouterAchat(
        id_client: Int,
        id_plan: Int
    ) {

        gymViewModel.getCommande(id_client, id_plan)
    }

    private fun getMealInformationFromIntent() {
        val args = this.arguments
        idcategory = args?.get("id_service").toString()
    }

}

