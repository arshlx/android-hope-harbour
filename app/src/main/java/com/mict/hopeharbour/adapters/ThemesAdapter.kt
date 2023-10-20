package com.mict.hopeharbour.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.databinding.ItemThemeBinding
import com.mict.hopeharbour.model.Theme

class ThemesAdapter(private val fragment: Fragment, private val themes: List<Theme>) :
    RecyclerView.Adapter<ThemesAdapter.ThemesViewHolder>() {
    inner class ThemesViewHolder(private val binding: ItemThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(theme: Theme) {
            binding.apply {
                themeTxt.apply {
                    text = theme.name
                    setOnClickListener {

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ThemesAdapter.ThemesViewHolder {
        return ThemesViewHolder(
            ItemThemeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ThemesAdapter.ThemesViewHolder, position: Int) {
        holder.bind(themes[position])
    }

    override fun getItemCount() = themes.size
}