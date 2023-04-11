package com.example.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountries.model.Country

@Dao
interface CountryDao {

    //Data Access Object
    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>
    //Insert -> Insert Into
    //suspend -> coroutine,pause & resume
    //vararg -> arrayler için kullanılır(multiple country object)
    //List<Logn> ->  return primary key

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid= :countryID")
    suspend fun getCountry(countryID: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}