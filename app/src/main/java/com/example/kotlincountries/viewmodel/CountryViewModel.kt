package com.example.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.service.CountryDatabase
import com.example.kotlincountries.util.uuidFromSingleton
import kotlinx.coroutines.launch

class CountryViewModel(application: Application):BaseViewModel(application) {
    val countryLiveData=MutableLiveData<Country>()
    fun getDataFromRoom(uuid:Int){
        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            countryLiveData.value=dao.getCountry(uuid)


        }

    }


}