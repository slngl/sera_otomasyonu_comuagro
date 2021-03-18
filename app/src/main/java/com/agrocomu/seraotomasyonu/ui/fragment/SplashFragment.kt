package com.agrocomu.seraotomasyonu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.agrocomu.seraotomasyonu.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        lifecycleScope.launch {
            delay(1000)

            //if user did not login
//            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            //else
            findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
        }
        return view
    }


}