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
import com.mict.hopeharbour.adapters.ProjectsAdapter
import com.mict.hopeharbour.databinding.FragmentProjectsBinding
import com.mict.hopeharbour.interfaces.CountryNameInterface
import com.mict.hopeharbour.main.vm.MainViewModel
import global_objects.TaskStatus

class ProjectsFragment : BaseFragment(), CountryNameInterface {
    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.projectStatus.observe(viewLifecycleOwner, projectsObserver)
        viewModel.getProjects()
    }

    override fun onResume() {
        super.onResume()
        parentActivity.binding.appBarLayout.appBar.apply {
            visibility = View.VISIBLE
            title = viewModel.countryName
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProjectsFragment()
    }

    private val projectsObserver = Observer<Int> {
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
                            ProjectsAdapter(
                                this@ProjectsFragment,
                                viewModel.projectList, this@ProjectsFragment
                            )
                        visibility = View.VISIBLE
                    }
                }
            }

            TaskStatus.EMPTY -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), R.string.no_projects, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onClick(country: String) {
        viewModel.selProject = country
        parentFragmentManager.beginTransaction().apply {
            replace(
                R.id.container,
                ProjectDetailFragment.newInstance()
            )
            addToBackStack(null)
            commit()
        }
    }
}