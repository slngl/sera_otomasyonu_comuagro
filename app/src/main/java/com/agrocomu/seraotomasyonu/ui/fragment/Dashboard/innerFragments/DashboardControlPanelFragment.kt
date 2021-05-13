package com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardControlPanelBinding

class DashboardControlPanelFragment : BaseFragment<FragmentDashboardControlPanelBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_control_panel

    companion object {
        fun newInstance() = DashboardControlPanelFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}