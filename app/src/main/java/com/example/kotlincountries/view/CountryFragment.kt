package com.example.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.kotlincountries.databinding.FragmentCountryBinding
import com.example.kotlincountries.util.downloadFromUrl
import com.example.kotlincountries.util.placeholderProgressBar
import com.example.kotlincountries.util.uuidFromSingleton
import com.example.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    private lateinit var bindingCountry: FragmentCountryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingCountry = FragmentCountryBinding.inflate(inflater, container, false)
        val view = bindingCountry.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            //countryUuid=CountryFragmentArgs.fromBundle(it).uuid
            countryUuid = uuidFromSingleton
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                bindingCountry.countryName.text = country.countryName
                bindingCountry.capitolName.text = country.countryCapitol
                bindingCountry.currency.text = country.countryCurrency
                bindingCountry.regionName.text = country.countryRegion
                bindingCountry.language.text = country.countryLanguage
                context?.let {
                    bindingCountry.countryImage.downloadFromUrl(
                        country.imageURL.toString(),
                        placeholderProgressBar(it)
                    )
                }

            }
        })
    }


}