package com.nalulabs.prefs.demo

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tokenHolder = TokenHolder(PreferenceManager.getDefaultSharedPreferences(this))
        tokenHolder.saveToken("Token_${System.currentTimeMillis()}")
        findViewById<TextView>(R.id.text).text = "${tokenHolder.token} ${tokenHolder.count}"
    }
}
