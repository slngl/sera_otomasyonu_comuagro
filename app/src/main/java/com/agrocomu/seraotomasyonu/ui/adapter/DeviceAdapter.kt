package com.agrocomu.seraotomasyonu.ui.adapter

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agrocomu.seraotomasyonu.databinding.ItemBtDeviceBinding

class DeviceAdapter(val context: Context) :
    RecyclerView.Adapter<DeviceAdapter.BluetoothDeviceViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<BluetoothDevice>() {
        override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
            return oldItem.uuids.contentEquals(newItem.uuids)
        }

        override fun areContentsTheSame(
            oldItem: BluetoothDevice,
            newItem: BluetoothDevice
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(deviceList: List<BluetoothDevice>) {
        recyclerListDiffer.submitList(deviceList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothDeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBtDeviceBinding.inflate(inflater, parent, false)
        return BluetoothDeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BluetoothDeviceViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList[position]
        holder.bindTo(item)
    }

    override fun getItemCount(): Int = recyclerListDiffer.currentList.size

    class BluetoothDeviceViewHolder(binding: ItemBtDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val btName = binding.tvDeviceName
        private val btAddress = binding.tvDeviceAddress

        fun bindTo(item: BluetoothDevice) {
            btAddress.text = item.address.toString()
            btName.text = item.name
        }

    }
}
