package com.mict.hopeharbour.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mict.hopeharbour.R
import com.mict.hopeharbour.databinding.ItemCountryBinding
import com.mict.hopeharbour.interfaces.CountryNameInterface
import com.mict.hopeharbour.main.ProjectsFragment
import com.mict.hopeharbour.model.CountriesResponse

class CountriesAdapter(
    private val fragment: Fragment,
    private val countryNameInterface: CountryNameInterface,
    private val countries: List<CountriesResponse>
) :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {
    inner class CountriesViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountriesResponse) {
            binding.apply {
                countryNameTxt.text = country.name.common
                countryOfficialNameTxt.text = country.name.official

                println("Country Name: " + country.name.common)
                container.setOnClickListener {
                    countryNameInterface.onClick(country.name.common)
                    fragment.parentFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.container,
                            ProjectsFragment.newInstance()
                        )
                        addToBackStack(null)
                        commit()
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesAdapter.CountriesViewHolder {
        return CountriesViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

}