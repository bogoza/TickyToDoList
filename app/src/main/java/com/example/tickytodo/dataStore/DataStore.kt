package com.example.tickytodo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(name = PREF_NAME)

    val valueFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[preferencesKey(KEY)] ?: false
    }

    suspend fun saveValue(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[preferencesKey(KEY)] = value
        }
    }

    companion object {
        private const val PREF_NAME = "Pref_name"
        private const val KEY = "Key"
    }
}
