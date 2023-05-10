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
import com.example.hotelwallet.domain.model.ToolbarConfiguration
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate,
    toolbarConfiguration = ToolbarConfiguration()
), View.OnClickListener {

    private val authenticationViewModel by activityViewModels<AuthenticationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            authenticationViewModel.stateSignUp.observe(viewLifecycleOwner) {
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
                            "the user is failed to login",
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
        name: String,
        email: String,
        password: String,
        image: String
    ) {

        authenticationViewModel.signUp(name, email, password,image)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_register -> {
                if (binding.editName.text.trim().isNotBlank() &&
                    binding.editEmail.text.trim().isNotBlank() &&
                    binding.editPassword.text.trim().isNotBlank()&&
                    binding.editPassword.text.trim().isNotBlank()

                ) {
                    signUp(
                        binding.editName.text.toString(),
                        binding.editEmail.text.toString(),
                        binding.editPassword.text.toString(),
                        binding.editImage.text.toString()
                    )
                    Log.println(Log.ASSERT, "name", binding.editName.text.toString())
                    Log.println(Log.ASSERT, "email", binding.editEmail.text.toString())
                    Log.println(Log.ASSERT, "password", binding.editPassword.text.toString())
                    val bundle = Bundle()
                    bundle.putString("name", binding.editName.text.toString())
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment,bundle)
                }
            }
            R.id.btn_login-> {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }
    }

}