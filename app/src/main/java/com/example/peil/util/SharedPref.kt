package com.example.peil.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

private val mainKeyAlias by lazy {
    val keyGenSpec = MasterKeys.AES256_GCM_SPEC
    MasterKeys.getOrCreate(keyGenSpec)
}

fun sharedPreferences(context: Context): SharedPreferences {
    val sharedPrefsFile = "user"

    return EncryptedSharedPreferences.create(
        sharedPrefsFile,
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}