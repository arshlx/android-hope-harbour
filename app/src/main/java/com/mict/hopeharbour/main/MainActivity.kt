package com.mict.hopeharbour.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mict.hopeharbour.R
import com.mict.hopeharbour.main.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.container,
                CountriesFragment.newInstance()
            )
            addToBackStack(null)
            commit()
        }
    }
}