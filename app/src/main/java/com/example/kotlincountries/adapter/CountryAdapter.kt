package com.example.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountries.R
import com.example.kotlincountries.databinding.ItemCountryBinding
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.util.uuidFromSingleton
import com.example.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.Holder>(), CountryClickListener {
    class Holder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.country = countryList[position]
        holder.binding.listener = this


        /* holder.binding.name.text=countryList.get(position).countryName
         holder.binding.region.text=countryList.get(position).countryRegion
         holder.binding.imageView.downloadFromUrl(countryList.get(position).imageURL.toString(),
             placeholderProgressBar(holder.binding.root.context)
         )
         holder.binding.root.setOnClickListener {
             val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
             Navigation.findNavController(it).navigate(action)
             uuidFromSingleton=countryList.get(position).uuid
         }*/

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

    override fun onCountryClicked(v: View) {
        uuidFromSingleton=v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
        Navigation.findNavController(v).navigate(action)




    }
}