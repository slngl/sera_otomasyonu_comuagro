package com.agrocomu.seraotomasyonu.ui.fragment.Main.innerFragments

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.base.BaseFragment
import com.agrocomu.seraotomasyonu.databinding.FragmentBluetoothBinding
import com.agrocomu.seraotomasyonu.ui.adapter.DeviceAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BluetoothFragment : BaseFragment<FragmentBluetoothBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_bluetooth

    companion object {
        fun newInstance() = BluetoothFragment()
    }

    private val REQUEST_ENABLE_BLUETOOTH = 1
    private lateinit var pairedDevices: Set<BluetoothDevice>
    private var bluetoothAdapter: BluetoothAdapter? = null
    private val deviceList: ArrayList<BluetoothDevice> = ArrayList()
    private val deviceAdapter by lazy { DeviceAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)



        return binding.root
    }

/*
    fun listDevice(context: Context) { //paired devices (listView)
        pairedDevices = bluetoothAdapter?.bondedDevices!!

        if (pairedDevices.size > 0) {
            pairedDevices.forEach {
                deviceList.add(it)
            }
        } else {
            Toast.makeText(context, "Eşleşen Cihaz Yok! ", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = deviceAdapter

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                if (bluetoothAdapter!!.isEnabled) {
                    Toast.makeText(requireContext(), "Bluetooth etkin", Toast.LENGTH_SHORT).show()
//                    discoverDevices()
                } else {
                    Toast.makeText(requireContext(), "Bluetooth etkin değil", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    requireContext(),
                    "Bluetooth etkinleştirilmesi iptal edildi",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }


    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothDevice.ACTION_FOUND == action) {
                // A Bluetooth device was found
                // Getting device information from the intent
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                if (device != null) {
                    deviceList.add(device)
//                    adapter.notifyDataSetChanged()
                    println(deviceList.size)

                }
            }
        }
    }

    //The fragment is visible in the running activity
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context?.registerReceiver(mReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(mReceiver)

    }
*/


}
