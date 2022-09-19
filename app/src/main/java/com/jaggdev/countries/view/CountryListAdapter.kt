package com.jaggdev.countries.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaggdev.countries.databinding.ItemCountryBinding
import com.jaggdev.countries.model.Country
import com.jaggdev.countries.util.getProgressDrawable
import com.jaggdev.countries.util.loadImage


class CountryListAdapter(var countries: ArrayList<Country>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        private val countryName = binding.countryName
        private val countryCapital = binding.capital
        private val countryFlag = binding.countryFlag
        private val progressDrawable = getProgressDrawable(binding.root.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
            countryCapital.text = country.capital
            countryFlag.loadImage(country.flag, progressDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }
}