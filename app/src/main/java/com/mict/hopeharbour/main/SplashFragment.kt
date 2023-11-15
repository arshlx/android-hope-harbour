package com.mict.hopeharbour.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mict.hopeharbour.R
import com.mict.hopeharbour.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        delayFunction()
    }

    private fun delayFunction() {
        CoroutineScope(Dispatchers.Main).launch {
            parentActivity.binding.appBarLayout.appBar.visibility = View.GONE
            delay(2000)
            parentFragmentManager.beginTransaction().apply {
                replace(
                    R.id.container,
                    CountriesFragment.newInstance()
                )
                addToBackStack(null)
                commit()
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}