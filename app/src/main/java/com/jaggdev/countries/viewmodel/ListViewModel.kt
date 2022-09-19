package com.jaggdev.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaggdev.countries.model.CountriesService
import com.jaggdev.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ListViewModel : ViewModel() {
    var countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(response: List<Country>) {
                        countries.value = response
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}