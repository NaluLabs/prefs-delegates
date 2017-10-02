package com.nalulabs.prefs

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private inline fun <T> SharedPreferences.delegate(
        defaultValue: T, key: String? = null,
        crossinline getter: SharedPreferences.(String, T) -> T,
        crossinline setter: Editor.(String, T) -> Editor
): ReadWriteProperty<Any, T> =
        object : ReadWriteProperty<Any, T> {
            override fun getValue(thisRef: Any, property: KProperty<*>): T =
                    getter(key ?: property.name, defaultValue)!!

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
                    edit().setter(key ?: property.name, value).apply()
        }

fun SharedPreferences.int(def: Int = 0, key: String? = null): ReadWriteProperty<Any, Int> =
        delegate(def, key, SharedPreferences::getInt, Editor::putInt)

fun SharedPreferences.long(def: Long = 0, key: String? = null): ReadWriteProperty<Any, Long> =
        delegate(def, key, SharedPreferences::getLong, Editor::putLong)

fun SharedPreferences.float(def: Float = 0f, key: String? = null): ReadWriteProperty<Any, Float> =
        delegate(def, key, SharedPreferences::getFloat, Editor::putFloat)

fun SharedPreferences.boolean(def: Boolean = false, key: String? = null): ReadWriteProperty<Any, Boolean> =
        delegate(def, key, SharedPreferences::getBoolean, Editor::putBoolean)

fun SharedPreferences.stringSet(def: Set<String> = emptySet(), key: String? = null): ReadWriteProperty<Any, Set<String>> =
        delegate(def, key, SharedPreferences::getStringSet, Editor::putStringSet)

fun SharedPreferences.string(def: String = "", key: String? = null): ReadWriteProperty<Any, String> =
        delegate(def, key, SharedPreferences::getString, Editor::putString)