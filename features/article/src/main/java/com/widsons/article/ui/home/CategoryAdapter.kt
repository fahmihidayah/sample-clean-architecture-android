package com.widsons.article.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widsons.article.data.model.Category
import com.widsons.article.databinding.CategoryViewHolderBinding
import com.widsons.ui.adapter.BaseAdapter
import com.widsons.ui.adapter.BaseViewHolder

class CategoryAdapter(items: List<Category> = listOf()) :
    BaseAdapter<CategoryAdapter.CategoryViewHolder, Category>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class CategoryViewHolder(val binding: CategoryViewHolderBinding) :
        BaseViewHolder(itemView = binding.root) {
        override fun bind(item: Any?, position: Int) {
            if (item is Category) {
                binding.textViewCategory.setText(item.name)
                binding.isSelected = position == selectedIndex
                binding.textViewCategory.setOnClickListener {
                    if(selectedIndex != -1) {
                        notifyItemChanged(selectedIndex)
                    }
                    selectedIndex = position
                    notifyItemChanged(selectedIndex)
                }
            }
        }
    }
}