package com.mict.hopeharbour.adapters

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.databinding.ItemUpdateBinding
import com.mict.hopeharbour.model.updates.UpdateEntry
import global_objects.BaseFragment
import java.util.Locale

class UpdatesAdapter(
    private val fragment: BaseFragment,
    private val updateList: List<UpdateEntry>
) :
    RecyclerView.Adapter<UpdatesAdapter.UpdatesViewHolder>() {
    inner class UpdatesViewHolder(private val binding: ItemUpdateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(updateItem: UpdateEntry) {
            binding.apply {
                dateTxt.text = convertDateFormat(updateItem.published)
                titleTxt.text = updateItem.title
                if (updateItem.authors.size == 1) {
                    authorsTxt.text = updateItem.authors[0].name
                } else {
                    var authorsString = ""
                    updateItem.authors.forEachIndexed { index, author ->
                        run {
                            authorsString += if (index == updateItem.authors.size - 1) {
                                author.name
                            } else {
                                updateItem.authors[index].name + ", "
                            }
                        }
                    }
                    authorsTxt.text = authorsString
                }
            }
            if (updateItem.link.isNotEmpty()) {
                binding.containerLayout.apply {
                    val organizationUri = Intent(Intent.ACTION_VIEW)
                    organizationUri.data = Uri.parse(updateItem.link)
                    setOnClickListener {
                        fragment.startActivity(organizationUri)
                    }
                }
            }
        }
    }

    fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        try {
            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
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
