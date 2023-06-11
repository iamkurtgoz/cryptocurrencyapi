package com.iamkurtgoz.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.PreferencesProto.StringSet
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.TreeSet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStoreManager @Inject constructor(
    @ApplicationContext private val application: Context,
    private val gson: Gson
) {
    // Create the data Store
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "${application.packageName}_settings")

    //Keys
    enum class KeyInt(val key: Preferences.Key<Int>) {

    }

    enum class KeyLong(val key: Preferences.Key<Long>) {

    }

    enum class KeyString(val key: Preferences.Key<String>) {

    }

    enum class KeyBoolean(val key: Preferences.Key<Boolean>) {

    }

    //Read
    fun readInt(enum: KeyInt, default: Int = -1): Flow<Int>{
        return application.dataStore.data.map {
            it[enum.key] ?: default
        }
    }

    fun readString(enum: KeyString, default: String? = null): Flow<String?>{
        return application.dataStore.data.map {
            it[enum.key] ?: default
        }
    }

    fun readBoolean(enum: KeyBoolean, default: Boolean = false): Flow<Boolean> {
        return application.dataStore.data.map {
            it[enum.key] ?: default
        }
    }

    fun readLong(enum: KeyLong, default: Long = -1): Flow<Long> {
        return application.dataStore.data.map {
            it[enum.key] ?: default
        }
    }

    fun read(key: Preferences.Key<String>): Flow<String?> {
        return application.dataStore.data.map {
            it[key]
        }
    }

    //Write
    suspend fun saveInt(enum: KeyInt, value: Int) {
        application.dataStore.edit {
            it[enum.key] = value
        }
    }

    suspend fun saveString(enum: KeyString, value: String) {
        application.dataStore.edit {
            it[enum.key] = value
        }
    }

    suspend fun saveBoolean(enum: KeyBoolean, value: Boolean) {
        application.dataStore.edit {
            it[enum.key] = value
        }
    }

    suspend fun saveLong(enum: KeyLong, value: Long) {
        application.dataStore.edit {
            it[enum.key] = value
        }
    }

    suspend fun save(key: Preferences.Key<String>, value: String) {
        application.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun logout() {
        application.dataStore.edit {
            it.clear()
        }
    }
}