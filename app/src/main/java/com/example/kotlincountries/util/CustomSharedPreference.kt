package com.example.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.Preference
import androidx.preference.PreferenceManager

class CustomSharedPreference {
    companion object {
        private var sharedPreferences: SharedPreferences? = null
        private var PREFERENCES_TIME="preferences_time"
        @Volatile
        private var instance: CustomSharedPreference? = null
        private val lock = Any()
        operator fun invoke(context: Context): CustomSharedPreference =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreference {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreference()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime()= sharedPreferences?.getLong(PREFERENCES_TIME,0)

}