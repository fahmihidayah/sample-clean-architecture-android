package com.widsons.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(item : Any?, position : Int)
}