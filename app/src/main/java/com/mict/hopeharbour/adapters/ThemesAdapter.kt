package com.mict.hopeharbour.adapters

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.databinding.ItemThemeBinding
import com.mict.hopeharbour.model.Theme

abstract class ThemesAdapter(private val fragment: Fragment, private val themes: List<Theme>) :
    RecyclerView.Adapter<ThemesAdapter.ThemesViewHolder>() {
    inner class ThemesViewHolder(private val binding: ItemThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(theme: Theme) {
            binding.apply {

            }
        }


    }
}