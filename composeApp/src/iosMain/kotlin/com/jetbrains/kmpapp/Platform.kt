package com.jetbrains.kmpapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue
import platform.darwin.NSObject


// shared/src/iosMain/kotlin/createDataStore.kt

@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
)






actual fun PrefGetInt(key: String) : Int {
    val a= NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()

    println("PrefGetInt ${key} $a")
    return  a;
}

actual fun PrefSetInt(key: String, value : Int){
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(),key)
}

actual fun PrefSetString(key: String, value : String){
    NSUserDefaults.standardUserDefaults.setValue(value,key)
}

actual fun PrefGetString(key: String) : String? {
    val a= NSUserDefaults.standardUserDefaults.stringForKey(key)

    println("PrefGetDouble ${key} $a")
    return  a;
}



actual fun PrefGetDouble(key: String) : Double {
    val a= NSUserDefaults.standardUserDefaults.doubleForKey(key)

    println("PrefGetDouble ${key} $a")
    return  a;
}

actual fun PrefSetDouble(key: String, value : Double){
    NSUserDefaults.standardUserDefaults.setDouble(value,key)
}
