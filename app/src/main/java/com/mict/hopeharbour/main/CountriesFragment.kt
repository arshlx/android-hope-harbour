package com.mict.hopeharbour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mict.hopeharbour.R
import com.mict.hopeharbour.adapters.CountriesAdapter
import com.mict.hopeharbour.databinding.FragmentCountriesBinding
import com.mict.hopeharbour.interfaces.CountryNameInterface
import com.mict.hopeharbour.main.vm.MainViewModel
import global_objects.TaskStatus

class CountriesFragment : Fragment(), CountryNameInterface {


    private var _binding: FragmentCountriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.countriesStatus.observe(viewLifecycleOwner, countriesObserver)
        viewModel.allCountriesStatus.observe(viewLifecycleOwner, allCountriesObserver)
        viewModel.getAllCountries()

        binding.btnFindCountry.setOnClickListener {
            if (!binding.countryEdt.text.isNullOrEmpty()) {
                binding.countryEdtLayout.error = null
                viewModel.getCountries(binding.countryEdt.text.toString().trim())
            } else binding.countryEdtLayout.error = getString(R.string.empty_field)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CountriesFragment()
    }

    private val countriesObserver = Observer<Int> {
        when (it) {
            TaskStatus.LOADING -> {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    countriesRecycler.apply {
                        adapter = null
                        visibility = View.GONE
                    }
                }
            }

            TaskStatus.SUCCESS -> {
                binding.apply {
                    progressBar.visibility = View.GONE
                    countriesRecycler.apply {
                        scheduleLayoutAnimation()

                        adapter =
                            CountriesAdapter(
                                this@CountriesFragment, this@CountriesFragment,
                                viewModel.countriesList
                            )
                        visibility = View.VISIBLE
                    }
                }
            }

            TaskStatus.EMPTY -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), R.string.no_countries, Toast.LENGTH_LONG).show()
            }
        }
    }

    private val allCountriesObserver = Observer<Int> {
        when (it) {
            TaskStatus.LOADING -> {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    countriesRecycler.apply {
                        adapter = null
                        visibility = View.GONE
                    }
                }
            }

            TaskStatus.SUCCESS -> {
                binding.apply {
                    progressBar.visibility = View.GONE
                    countriesRecycler.apply {
                        scheduleLayoutAnimation()

                        adapter =
                            CountriesAdapter(
                                this@CountriesFragment, this@CountriesFragment,
                                viewModel.countriesList
                            )
                        visibility = View.VISIBLE
                    }
                }
            }

            TaskStatus.EMPTY -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), R.string.no_countries, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(country: String) {
        viewModel.countryName = country
    }
}
