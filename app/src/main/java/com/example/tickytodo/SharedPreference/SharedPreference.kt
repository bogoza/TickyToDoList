package com.example.tickytodo.SharedPreference

import android.content.Context
import com.example.tickytodo.SharedPreference.SharedPreference.PrefConstants.PREF_NAME


class SharedPreference(context: Context) {

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()


    fun saveValue(value: Boolean) {
        editor.putBoolean(PrefConstants.KEY, value)
        editor.apply()
    }

    fun getValue(): Boolean {
        return preferences.getBoolean(PrefConstants.KEY, false)
    }

    object PrefConstants {
        const val PREF_NAME = "Pref_name"
        const val KEY = "Key"
    }

}