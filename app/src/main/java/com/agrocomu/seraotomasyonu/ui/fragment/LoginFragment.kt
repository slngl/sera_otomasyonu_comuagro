package com.agrocomu.seraotomasyonu.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.LoginFragmentBinding
import com.agrocomu.seraotomasyonu.ui.MainActivity
import com.agrocomu.seraotomasyonu.ui.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    private val viewModel by viewModels<LoginViewModel>()
    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.btDoLogin.setOnClickListener {
//            viewModel.doLogin()
            val intent= Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

        val view= inflater.inflate(R.layout.login_fragment, container, false)
        return binding.root
    }


    override val layoutResource: Int = R.layout.login_fragment


}