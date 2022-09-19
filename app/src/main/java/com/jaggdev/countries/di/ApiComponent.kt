package com.jaggdev.countries.di

import com.jaggdev.countries.model.CountriesService
import dagger.Component


@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
}