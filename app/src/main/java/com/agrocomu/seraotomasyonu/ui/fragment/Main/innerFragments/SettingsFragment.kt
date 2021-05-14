package com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentDashboardSettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentDashboardSettingsBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_dashboard_settings

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.ivBluetooth.setOnClickListener {
            val intentOpenBluetoothSettings = Intent()
            intentOpenBluetoothSettings.action = Settings.ACTION_BLUETOOTH_SETTINGS
            startActivity(intentOpenBluetoothSettings)
        }
        return binding.root
    }
}