package com.agrocomu.seraotomasyonu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agrocomu.seraotomasyonu.R
import com.agrocomu.seraotomasyonu.databinding.ItemControlPanelReadDataBinding
import com.agrocomu.seraotomasyonu.databinding.ItemControlPanelSendDataBinding
import com.agrocomu.seraotomasyonu.entity.ControlPanelAdapterItem
import com.agrocomu.seraotomasyonu.entity.ControlPanelAdapterItemType

class ControlPanelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_control_panel_read_data -> {
                val binding = ItemControlPanelReadDataBinding.inflate(inflater, parent, false)
                ContolPanelReadDataViewHolder(binding)
            }
            else -> {
                val binding = ItemControlPanelSendDataBinding.inflate(inflater, parent, false)
                ControlPanelSendDataViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList.get(position)
        when (holder) {
            is ContolPanelReadDataViewHolder -> holder.bindTo(item)
            is ControlPanelSendDataViewHolder -> holder.bindTo(item)
        }
    }

    override fun getItemCount(): Int = recyclerListDiffer.currentList.size

    override fun getItemViewType(position: Int): Int {
        return when (recyclerListDiffer.currentList[position].controlPanelAdapterItemType) {
            ControlPanelAdapterItemType.READ_DATA -> R.layout.item_control_panel_read_data
            else -> R.layout.item_control_panel_send_data
        }
    }
}

class ContolPanelReadDataViewHolder(val binding: ItemControlPanelReadDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: ControlPanelAdapterItem?) {
        binding.tvTitle.text = item?.title
    }
}

class ControlPanelSendDataViewHolder(val binding: ItemControlPanelSendDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: ControlPanelAdapterItem?) {
        binding.tvTitle.text = item?.title

    }
}