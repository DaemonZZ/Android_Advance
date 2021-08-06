package com.example.androidarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.databinding.RvItemBinding
import com.example.androidarchitecture.model.Country

class CountryAdapter(private val listCountries:List<Country>): RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(listCountries[position])
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

}

class CountryViewHolder(private val binding: RvItemBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(country: Country){
        binding.country = country
    }
}