package com.jaggdev.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaggdev.countries.model.Country

class ListViewModel : ViewModel() {
    var countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(
            Country("CountryA"),
            Country("CountryB"),
            Country("CountryC"),
            Country("CountryD"),
            Country("CountryE"),
            Country("CountryF"),
            Country("CountryG"),
            Country("CountryH"),
            Country("CountryJ")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}