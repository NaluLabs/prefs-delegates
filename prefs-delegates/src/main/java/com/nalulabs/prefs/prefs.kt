/*
 * Copyright 2017 Fabio Collini
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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