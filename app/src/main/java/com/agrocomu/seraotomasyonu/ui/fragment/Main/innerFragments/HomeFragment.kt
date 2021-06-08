package com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardHomeBinding
import com.agrocomu.seraotomasyonu.ui.adapter.ControlPanelAdapter
import com.agrocomu.seraotomasyonu.ui.adapter.HomeBluetoothDataAdapter
import com.agrocomu.seraotomasyonu.ui.adapter.HomeWeatherAdapter
import com.agrocomu.seraotomasyonu.ui.viewModel.MainBaseViewModel
import com.agrocomu.seraotomasyonu.ui.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentDashboardHomeBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_home

    private val baseViewModel: MainBaseViewModel by viewModels()
    private val weatherViewModel : WeatherViewModel by viewModels()

    private val concatAdapter = ConcatAdapter()
    private val homeWeatherAdapter = HomeWeatherAdapter()
//    private val homeBluetoothDataAdapter = HomeBluetoothDataAdapter()
    private val controlPanelAdapter = ControlPanelAdapter()
    companion object{
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        weatherViewModel.getWeather("çanakkale")
        baseViewModel.startPolling()

        concatAdapter.addAdapter(homeWeatherAdapter)
        concatAdapter.addAdapter(controlPanelAdapter)

        binding.rv.adapter = concatAdapter

        baseViewModel.liveControlPanelData.observe(viewLifecycleOwner,{
            controlPanelAdapter.submitList(it)
        })

        weatherViewModel.liveWeatherResponse.observe(viewLifecycleOwner, {
            homeWeatherAdapter.submitList(listOf(it))
        })

        return binding.root
    }
}
