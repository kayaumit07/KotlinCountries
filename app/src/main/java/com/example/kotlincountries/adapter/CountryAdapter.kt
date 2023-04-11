package com.example.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountries.databinding.ItemCountryBinding
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.util.downloadFromUrl
import com.example.kotlincountries.util.placeholderProgressBar
import com.example.kotlincountries.util.uuidFromSingleton
import com.example.kotlincountries.view.FeedFragmentDirections

class CountryAdapter(private val countryList:ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.Holder>() {
    class Holder(val binding:ItemCountryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.name.text=countryList.get(position).countryName
        holder.binding.region.text=countryList.get(position).countryRegion
        holder.binding.imageView.downloadFromUrl(countryList.get(position).imageURL.toString(),
            placeholderProgressBar(holder.binding.root.context)
        )
        holder.binding.root.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
            uuidFromSingleton=countryList.get(position).uuid
        }

    }

    override fun getItemCount(): Int {
       return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}