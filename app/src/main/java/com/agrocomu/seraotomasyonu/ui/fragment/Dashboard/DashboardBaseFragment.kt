package com.agrocomu.seraotomasyonu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardBaseBinding
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemEntity
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemType
import com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments.DashboardControlPanelFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments.DashboardHomeFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments.DashboardSettingsFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Dashboard.innerFragments.WeatherFragment
import com.agrocomu.seraotomasyonu.ui.viewModel.DashboardViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DashboardBaseFragment : BaseFragment<FragmentDashboardBaseBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_base

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.liveDataMenuItems.observe(viewLifecycleOwner, {
            setupPager(it)
        })
/*        binding.ivFirstRoof.setOnClickListener {
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

        })*/

        return binding.root
    }

    private fun setupPager(listOfPages: List<DashboardMenuItemEntity>) {
        if (binding.vpPages.adapter == null) {
            val adapter = DashboardCollectionAdapter(this, listOfPages)
            binding.vpPages.adapter = adapter
            binding.vpPages.offscreenPageLimit = adapter.itemCount

            TabLayoutMediator(binding.tbTabs, binding.vpPages) { tab, position ->
                tab.text = getString(adapter.listOfPages[position].title)
            }.attach()
        }
    }
}

class DashboardCollectionAdapter(
    fragment: Fragment,
    val listOfPages: List<DashboardMenuItemEntity>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return listOfPages.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (listOfPages[position].key) {
            DashboardMenuItemType.weather -> WeatherFragment.newInstance()
            DashboardMenuItemType.settings -> DashboardSettingsFragment.newInstance()
            DashboardMenuItemType.controlpanel -> DashboardControlPanelFragment.newInstance()
            DashboardMenuItemType.home -> DashboardHomeFragment.newInstance()
        }
    }
}