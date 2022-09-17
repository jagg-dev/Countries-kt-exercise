package com.jaggdev.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaggdev.countries.R
import com.jaggdev.countries.databinding.ActivityMainBinding
import com.jaggdev.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    lateinit var binding: ActivityMainBinding
    private val countriesAdapter =  CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()

        binding.countryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            countries?.let { countriesAdapter.updateCountries(it) }
        }

        viewModel.countryLoadError.observe(this) { isError ->
            isError?.let { binding.listError.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE

                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}