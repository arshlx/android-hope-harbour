package com.mict.hopeharbour.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.databinding.ItemDonationBinding
import com.mict.hopeharbour.main.DonationsFragment
import com.mict.hopeharbour.model.DonationOption

class DonationOptionsAdapter(private val fragment: DonationsFragment, private val donationOptionList: List<DonationOption>) :
    RecyclerView.Adapter<DonationOptionsAdapter.DonationViewHolder>() {
    inner class DonationViewHolder(private val binding: ItemDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donationOption: DonationOption) {
            binding.apply {
                amountTxt.text = donationOption.amount.toString()
                donationTxt.text = donationOption.description
                val donationUri = Intent(Intent.ACTION_VIEW)
                donationUri.data = Uri.parse("https://www.globalgiving.org/")
                container.setOnClickListener {
                    fragment.startActivity(donationUri)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        return DonationViewHolder(
            ItemDonationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = donationOptionList.size

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donationOptionList[position])
    }
}