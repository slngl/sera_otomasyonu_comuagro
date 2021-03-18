package com.agrocomu.seraotomasyonu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardBinding
import com.agrocomu.seraotomasyonu.ui.viewModel.DashboardViewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(){
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard

    private val viewModel : DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_weatherFragment)
        }
        return binding.root
    }
}