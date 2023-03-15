package com.widsons.ui.adapter

import androidx.recyclerview.widget.RecyclerView.Adapter

abstract class BaseAdapter<VH : BaseViewHolder, I>(
    initialItems : List<I> = listOf()
) : Adapter<VH>() {
    var items : List<I> = initialItems


    fun updateWhenChange(items : List<I>) {
        if(items.size != this.items.size) {
            updateItems(items)
        }
    }

    fun updateItems(items : List<I>) {
        this.items = items
        notifyDataSetChanged()
    }

    var selectedIndex : Int = -1

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}