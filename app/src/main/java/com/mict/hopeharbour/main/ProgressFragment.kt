package com.mict.hopeharbour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mict.hopeharbour.R
import com.mict.hopeharbour.adapters.UpdatesAdapter
import com.mict.hopeharbour.databinding.FragmentProgressBinding
import com.mict.hopeharbour.main.vm.MainViewModel
import global_objects.BaseFragment
import global_objects.TaskStatus

class ProgressFragment : BaseFragment() {


    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.projectUpdateStatus.observe(viewLifecycleOwner, updatesObserver)
        binding.titleTxt.text = getString(R.string.updates_title_str, viewModel.project!!.title)
        viewModel.getProjectUpdates()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProgressFragment()
    }

    private val updatesObserver = Observer<Int> {
        when (it) {
            TaskStatus.LOADING -> {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    updatesRecycler.apply {
                        adapter = null
                        visibility = View.GONE
                    }
                }
            }

            TaskStatus.SUCCESS -> {
                binding.apply {
                    progressBar.visibility = View.GONE
                    updatesRecycler.apply {
                        scheduleLayoutAnimation()
                        adapter =
                            UpdatesAdapter(
                                this@ProgressFragment,
                                viewModel.updateList
                            )
                        visibility = View.VISIBLE
                    }
                }
            }

            TaskStatus.EMPTY -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), R.string.no_projects, Toast.LENGTH_LONG).show()
                parentActivity.onBackPressedDispatcher
            }
        }
    }
}