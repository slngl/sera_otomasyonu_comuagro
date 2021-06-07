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
import com.agrocomu.seraotomasyonu.base.BluetoothControl
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardBaseBinding
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemEntity
import com.agrocomu.seraotomasyonu.entity.DashboardMenuItemType
import com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments.ControlPanelFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments.HomeFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments.SettingsFragment
import com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments.WeatherFragment
import com.agrocomu.seraotomasyonu.ui.viewModel.MainBaseViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardBaseFragment : BaseFragment<FragmentDashboardBaseBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_base

    private val baseViewModel: MainBaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        baseViewModel.liveDataMenuItems.observe(viewLifecycleOwner, {
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
        BluetoothControl.connectDevice("98:D3:31:F7:62:36")

        return binding.root
    }

    private fun setupPager(listOfPages: List<DashboardMenuItemEntity>) {
        if (binding.vpPages.adapter == null) {
            val adapter = CollectionAdapter(this, listOfPages)
            binding.vpPages.adapter = adapter
            binding.vpPages.offscreenPageLimit = adapter.itemCount

            TabLayoutMediator(binding.tbTabs, binding.vpPages) { tab, position ->
                tab.text = getString(adapter.listOfPages[position].title)
            }.attach()
        }
    }
}

class CollectionAdapter(
    fragment: Fragment,
    val listOfPages: List<DashboardMenuItemEntity>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return listOfPages.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (listOfPages[position].key) {
            DashboardMenuItemType.weather -> WeatherFragment.newInstance()
            DashboardMenuItemType.settings -> SettingsFragment.newInstance()
            DashboardMenuItemType.controlpanel -> ControlPanelFragment.newInstance()
            DashboardMenuItemType.home -> HomeFragment.newInstance()
        }
    }
}