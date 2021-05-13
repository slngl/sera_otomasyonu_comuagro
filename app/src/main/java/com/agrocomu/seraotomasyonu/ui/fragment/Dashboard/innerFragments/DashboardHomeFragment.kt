package com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments

import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardHomeBinding

class DashboardHomeFragment : BaseFragment<FragmentDashboardHomeBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_home

    companion object{
        fun newInstance() = DashboardHomeFragment()
    }
}
