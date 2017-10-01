package com.nalulabs.prefs.demo

import android.content.SharedPreferences
import com.nalulabs.prefs.int
import com.nalulabs.prefs.string

class TokenHolder(prefs: SharedPreferences) {
    var token by prefs.string()
        private set

    var count by prefs.int()
        private set

    fun saveToken(newToken: String) {
        token = newToken
        count++
    }
}