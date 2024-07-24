package com.jetbrains.kmpapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

// shared/src/androidMain/kotlin/createDataStore.kt

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

internal const val dataStoreFileName = "dice.preferences_pb"




expect fun PrefGetInt(key: String) : Int
expect fun PrefGetDouble(key: String) : Double
expect fun PrefGetString(key: String) : String?

expect fun PrefSetInt(key: String, value: Int)
expect fun PrefSetDouble(key: String, value: Double)
expect fun PrefSetString(key: String, value: String)


