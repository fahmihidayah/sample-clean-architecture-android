package com.widsons.ui.adapter

import androidx.recyclerview.widget.RecyclerView.Adapter

abstract class BaseAdapter<VH : BaseViewHolder, I>(var items : List<I> = listOf()) : Adapter<VH>() {

    var selectedIndex : Int = -1

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}