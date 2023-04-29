package com.example.hotelwallet.presentation.sign

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentLoginBinding
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.Resource


class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
), View.OnClickListener {

    private val loginViewModel by activityViewModels<LoginViewModel>()
    private lateinit var name: String
    private lateinit var solde: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)


        lifecycleScope.launchWhenStarted {
            loginViewModel.stateLogin.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Toast.makeText(requireContext(),"the user is  login",Toast.LENGTH_LONG).show()
                        name =  it.data.name
                        solde =  it.data.solde
                        val bundle = Bundle()
                        bundle.putString("name", name)
                        bundle.putString("solde", solde)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment,bundle)

                        val sharedPreferences =
                            requireActivity().getSharedPreferences("authToken", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor?.putString("email", it.data.email)
                        editor?.putBoolean("isLoggedIn", true)
                        editor?.apply()
//                        editor.putString("authToken", it.data.access_token)
//                        editor.apply()
//                        Log.i("token",it.data.access_token)
//                        Log.i("toked stored",sharedPreferences.getString("authToken",null).toString())

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(),"the user is failed to login", Toast.LENGTH_LONG).show()


                    }
                }
            }

        }

        binding.btnLogin.setOnClickListener(this)

    }

    private fun login(email: String, password: String) {
        loginViewModel.getLogin(email = email, password = password)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> {

//                val args = this.arguments
//                name = args?.get("name").toString()
//                Log.println(Log.ASSERT,"name",name)

                if (binding.editEmail.text.trim().isNotBlank() &&
                    binding.editPassword.text.trim().isNotBlank()
                ) {
                    login(binding.editEmail.text.toString(), binding.editPassword.text.toString())
                }

//                val bundle = Bundle()
//                bundle.putString("name", name)
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment,bundle)

            }
        }
    }
}


