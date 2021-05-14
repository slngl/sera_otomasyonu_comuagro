package com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardControlPanelBinding
import com.agrocomu.seraotomasyonu.ui.viewModel.MainBaseViewModel

class ControlPanelFragment : BaseFragment<FragmentDashboardControlPanelBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_control_panel
    private val baseViewModel: MainBaseViewModel by viewModels()

    companion object {
        fun newInstance() = ControlPanelFragment()
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