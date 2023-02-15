package com.example.myapplication.presentation.carouselllist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.dto.CarousellDto
import com.example.myapplication.databinding.ItemCarousellBinding

class CarousellAdapter(private val listener: OnItemClickListener) :
    ListAdapter<CarousellDto, CarousellAdapter.CarousellViewHolder>(OBJECT_COMPARATOR) {

    inner class CarousellViewHolder(private val binding: ItemCarousellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(item: CarousellDto) {
            binding.apply {
                textView.text = item.title
                rankTextView.text = item.rank.toString()
            }
        }
    }

    companion object {
        private val OBJECT_COMPARATOR = object : DiffUtil.ItemCallback<CarousellDto>() {
            override fun areItemsTheSame(oldItem: CarousellDto, newItem: CarousellDto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CarousellDto, newItem: CarousellDto) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarousellViewHolder {

        val binding =
            ItemCarousellBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CarousellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarousellViewHolder, position: Int) {


        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(photo: CarousellDto)
}