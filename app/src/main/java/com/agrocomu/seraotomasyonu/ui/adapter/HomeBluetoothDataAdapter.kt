package com.agrocomu.seraotomasyonu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.databinding.ItemHomeReadDataBinding
import com.agrocomu.seraotomasyonu.databinding.ItemHomeSendDataBinding
import com.agrocomu.seraotomasyonu.databinding.ItemHomeWeatherBinding
import com.agrocomu.seraotomasyonu.entity.ControlPanelAdapterItem
import com.agrocomu.seraotomasyonu.entity.Weather

class HomeBluetoothDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_home_read_data -> {
                val binding = ItemHomeReadDataBinding.inflate(inflater, parent, false)
                HomeReadDataViewHolder(binding)
            }
            else -> {
                val binding = ItemHomeSendDataBinding.inflate(inflater, parent, false)
                HomeSendDataViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList.get(position)
        when (holder) {
            is HomeReadDataViewHolder -> holder.bindTo(item)
            is HomeSendDataViewHolder -> holder.bindTo(item)

        }
    }

    override fun getItemCount() = recyclerListDiffer.currentList.size


    private val diffUtil = object : DiffUtil.ItemCallback<ControlPanelAdapterItem>() {
        override fun areItemsTheSame(
            oldItem: ControlPanelAdapterItem,
            newItem: ControlPanelAdapterItem
        ): Boolean {
            return oldItem.amount == newItem.amount && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ControlPanelAdapterItem,
            newItem: ControlPanelAdapterItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(itemList: List<ControlPanelAdapterItem>) {
        recyclerListDiffer.submitList(itemList)
    }

}

class HomeSendDataViewHolder(val binding: ItemHomeSendDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: ControlPanelAdapterItem?) {

    }
}

class HomeReadDataViewHolder(val binding: ItemHomeReadDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: ControlPanelAdapterItem?) {

    }
}

