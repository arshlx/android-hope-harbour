package com.mict.hopeharbour.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.databinding.ItemUpdateBinding
import com.mict.hopeharbour.model.updates.UpdateEntry

class UpdatesAdapter(private val updateList: List<UpdateEntry>) :
    RecyclerView.Adapter<UpdatesAdapter.UpdatesViewHolder>() {
    inner class UpdatesViewHolder(private val binding: ItemUpdateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(updateItem: UpdateEntry) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdatesViewHolder {
        return UpdatesViewHolder(
            ItemUpdateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = updateList.size

    override fun onBindViewHolder(holder: UpdatesViewHolder, position: Int) {
        holder.bind(updateList[position])
    }


}