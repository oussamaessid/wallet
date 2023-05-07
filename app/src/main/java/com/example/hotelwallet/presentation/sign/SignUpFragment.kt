package com.example.hotelwallet.presentation.sign

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentSignUpBinding
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
), View.OnClickListener {

    private val loginViewModel by activityViewModels<LoginViewModel>()
    private lateinit var result: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        informationFromIntent()
        lifecycleScope.launchWhenStarted {
            loginViewModel.stateSignUp.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Snackbar.make(view, it.data.message, Snackbar.LENGTH_LONG).show()
                        Toast.makeText(requireContext(), "the user is register", Toast.LENGTH_LONG).show()

                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "the user is failed to register",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }

        binding.btnRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)

    }

    private fun signUp(
        nom: String,
        prenom: String,
        email: String,
        password: String,
        id_hotel: String
    ) {

        loginViewModel.signUp(nom,prenom,email, password,id_hotel)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_register -> {
                if (binding.editNom.text.trim().isNotBlank() &&
                    binding.ediPrenom.text.trim().isNotBlank() &&
                    binding.editEmail.text.trim().isNotBlank() &&
                    binding.editPassword.text.trim().isNotBlank()

                ) {
                    signUp(
                        binding.editNom.text.toString(),
                        binding.ediPrenom.text.toString(),
                        binding.editEmail.text.toString(),
                        binding.editPassword.text.toString(),
                        result
                    )
                    Log.println(Log.ASSERT, "name", binding.editNom.text.toString())
                    Log.println(Log.ASSERT, "email", binding.editEmail.text.toString())
                    Log.println(Log.ASSERT, "password", binding.editPassword.text.toString())
                    val bundle = Bundle()
                    bundle.putString("name", binding.editNom.text.toString())
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment,bundle)
                }
            }
            R.id.btn_login-> {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }
    }

    private fun informationFromIntent() {
        val args = this.arguments
        result = args?.get("RESULT").toString()
    }

}