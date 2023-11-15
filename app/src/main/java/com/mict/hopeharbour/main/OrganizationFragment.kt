package com.mict.hopeharbour.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mict.hopeharbour.R
import com.mict.hopeharbour.databinding.FragmentOrganizationBinding
import com.mict.hopeharbour.main.vm.MainViewModel
import com.mict.hopeharbour.model.Organization

class OrganizationFragment : BaseFragment() {

    private var _binding: FragmentOrganizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var organization: Organization
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        organization = viewModel.project!!.organization
        setUpViews()
    }

    override fun onResume() {
        super.onResume()
        parentActivity.binding.appBarLayout.appBar.apply {
            visibility = View.VISIBLE
            title = viewModel.project!!.organization.name
        }
    }

    private fun setUpViews() {
        binding.apply {
            organization.apply {
                organizationNameTxt.text = name
                Glide.with(requireContext()).load(logoUrl)
                    .placeholder(R.drawable.vec_img_loading).into(organizationImg)
                organizationAddressTxt.text = getString(
                    R.string.address_str,
                    addressLine1,
                    addressLine2,
                    city,
                    state,
                    country,
                    postal
                )
                organizationLinkTxt.apply {
                    if (url.isNullOrEmpty())
                        visibility = View.GONE
                    else {
                        text = url
                        val organizationUri = Intent(Intent.ACTION_VIEW)
                        organizationUri.data = Uri.parse(url)
                        setOnClickListener {
                            startActivity(organizationUri)
                        }
                    }
                }
                organizationMissionTxt.text = mission
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            OrganizationFragment()
    }
}