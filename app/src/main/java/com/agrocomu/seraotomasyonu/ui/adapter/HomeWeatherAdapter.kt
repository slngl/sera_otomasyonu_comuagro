package com.agrocomu.seraotomasyonu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.agrocomu.seraotomasyonu.databinding.ItemHomeWeatherBinding
import com.agrocomu.seraotomasyonu.entity.WeatherResponse

class HomeWeatherAdapter : RecyclerView.Adapter<HomeWeatherViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<WeatherResponse>(){
        override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WeatherResponse,
            newItem: WeatherResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    fun submitList(itemList: List<WeatherResponse>){
        recyclerListDiffer.submitList(itemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeWeatherBinding.inflate(inflater, parent, false)
        return HomeWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeWeatherViewHolder, position: Int) {
        val item = recyclerListDiffer.currentList[position]
        holder.bindTo(item = item)
    }

    override fun getItemCount() = recyclerListDiffer.currentList.size
}

class HomeWeatherViewHolder(val binding: ItemHomeWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: WeatherResponse) {

    }
}