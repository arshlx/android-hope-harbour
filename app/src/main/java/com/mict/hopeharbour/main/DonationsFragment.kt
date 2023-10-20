package com.mict.hopeharbour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mict.hopeharbour.R
import com.mict.hopeharbour.adapters.DonationOptionsAdapter
import com.mict.hopeharbour.databinding.FragmentDonationsBinding
import com.mict.hopeharbour.databinding.FragmentOrganizationBinding
import com.mict.hopeharbour.main.vm.MainViewModel

class DonationsFragment : Fragment() {

    private var _binding: FragmentDonationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDonationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.donationsRecycler.adapter = DonationOptionsAdapter(this, viewModel.project!!.donationOptions.donationOption)
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            DonationsFragment()
    }
}