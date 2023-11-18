package com.mict.hopeharbour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mict.hopeharbour.R
import com.mict.hopeharbour.adapters.ThemesAdapter
import com.mict.hopeharbour.databinding.FragmentProjectDetailBinding
import com.mict.hopeharbour.main.vm.MainViewModel
import com.mict.hopeharbour.model.Project

class ProjectDetailFragment : BaseFragment() {

    private var _binding: FragmentProjectDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var project: Project
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.project = Gson().fromJson(viewModel.selProject, Project::class.java)
        project = viewModel.project!!
        setUpViews()
    }

    private fun setUpViews() {
        binding.apply {
            projectNameTxt.text = project.title
            Glide.with(requireContext()).load(project.imageLink)
                .placeholder(R.drawable.vec_img_loading).into(projectImg)
            countryNameTxt.text = project.country
            projectSummaryTxt.text = project.summary
            val list = project.themes.theme
            if (!list.isNullOrEmpty()) {
                themesRecycler.adapter = ThemesAdapter(this@ProjectDetailFragment, list)
            }
            if (project.donationOptions.donationOption.isNullOrEmpty()) {
                donationsCard.visibility = View.GONE
            } else {
                donationsContainer.setOnClickListener {
                    parentFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.container,
                            DonationsFragment.newInstance()
                        )
                        addToBackStack(null)
                        commit()
                    }
                }
            }
            organizationContainer.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.container,
                        OrganizationFragment.newInstance()
                    )
                    addToBackStack(null)
                    commit()
                }
            }

            reportContainer.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    replace(
                        R.id.container,
                        ProgressFragment.newInstance()
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProjectDetailFragment()
    }
}