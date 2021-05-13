package com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments

import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardSettingsBinding

class DashboardSettingsFragment : BaseFragment<FragmentDashboardSettingsBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_settings

    companion object {
        fun newInstance() = DashboardSettingsFragment()
    }
}