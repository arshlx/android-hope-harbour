package com.mict.hopeharbour.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mict.hopeharbour.R
import com.mict.hopeharbour.databinding.ActivityMainBinding
import com.mict.hopeharbour.main.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)
        binding.appBarLayout.appBar.navigationIcon  = getDrawable(R.drawable.vec_arrow_left)
        binding.appBarLayout.appBar.title = getString(R.string.app_name)
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.container,
                SplashFragment.newInstance()
            )
            addToBackStack(null)
            commit()
        }
    }
}