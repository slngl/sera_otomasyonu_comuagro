package com.agrocomu.seraotomasyonu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        binding.ivFirstRoof.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_weatherFragment)
        }

        if (binding.chipFirstRoof.isChecked == true) {
            viewModel.closeFirstRoof()
        } else {
            viewModel.openFirstRoof()
        }

        viewModel.liveFirstRoofState.observe(viewLifecycleOwner, Observer {
            if (it=="a") {
                binding.ivFirstRoof.setImageResource(R.drawable.ic_broken_roof)
            }
            if (it=="b"){
                binding.ivFirstRoof.setImageResource(R.drawable.ic_baseline_home_24)
            }

        })

        return binding.root
    }
}