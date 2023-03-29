package com.example.hotelwallet.presentation.gym

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentCoachBinding
import com.example.hotelwallet.databinding.FragmentCoursBinding
import com.example.hotelwallet.domain.model.WelcomeSlide
import com.example.hotelwallet.presentation.misc.BaseFragment


class CoursFragment : BaseFragment<FragmentCoursBinding>(
    FragmentCoursBinding::inflate
) {
    private lateinit var offersAdapter: OffersAdapter
    private var menuList = mutableListOf<WelcomeSlide>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        offersAdapter = OffersAdapter(menuList) {
            val message: String? = "Are you sure "
            showCustomDialogBox(message)
        }

        binding.recyclerViewOffers.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOffers.setHasFixedSize(true)
        binding.recyclerViewOffers.isNestedScrollingEnabled = false
        setSlideList()
        binding.recyclerViewOffers.adapter = offersAdapter

    }


    private fun setSlideList() {
        menuList.add(
            WelcomeSlide(
                R.drawable.img_cours,
                "Work at Home",
                "Work at home to more comfort. Make a great projects with this app"
            )
        )
        menuList.add(
            WelcomeSlide(
                R.drawable.img_cours,
                "Analyse Your Project",
                "Smart details for analysis. Do more with this app"
            )
        )
        menuList.add(
            WelcomeSlide(
                R.drawable.img_cours,
                "Achieve Your Goals",
                "Achieve your goals more easily. This app will help with that"
            )
        )
        menuList.add(
            WelcomeSlide(
                R.drawable.img_cours,
                "Achieve Your Goals",
                "Achieve your goals more easily. This app will help with that"
            )
        )
        menuList.add(
            WelcomeSlide(
                R.drawable.img_cours,
                "Achieve Your Goals",
                "Achieve your goals more easily. This app will help with that"
            )
        )
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
            Toast.makeText(requireContext(), "click on Yes", Toast.LENGTH_LONG).show()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    companion object {
        fun newInstance() =
            CoursFragment()

    }
}