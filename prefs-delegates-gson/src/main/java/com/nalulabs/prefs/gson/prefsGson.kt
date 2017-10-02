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

package com.nalulabs.prefs.gson

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> SharedPreferences.json(defaultValue: T, key: String? = null, cacheValue: Boolean = false) =
        object : ReadWriteProperty<Any, T> {
            private val gson = Gson()

            private var cache: T? = null

            override fun getValue(thisRef: Any, property: KProperty<*>): T {
                cache?.let { return it }
                val s = getString(key ?: property.name, "")!!
                return if (s.isBlank())
                    defaultValue
                else {
                    val newValue = gson.fromJson(s, T::class.java)
                    if (cacheValue)
                        cache = newValue
                    newValue
                }
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
                edit().putString(key ?: property.name, gson.toJson(value)).apply()
                if (cacheValue)
                    cache = value
            }
        }

inline fun <reified T> SharedPreferences.jsonList(key: String? = null, cacheValue: Boolean = false) =
        object : ReadWriteProperty<Any, List<T>> {

            private val gson = Gson()

            private var cache: List<T>? = null

            override fun getValue(thisRef: Any, property: KProperty<*>): List<T> {
                cache?.let { return it }
                val s = getString(key ?: property.name, "")!!
                return if (s.isBlank()) {
                    emptyList()
                } else {
                    val newValue = gson.fromJson<List<T>>(s, object : TypeToken<List<T>>() {}.type)
                    if (cacheValue)
                        cache = newValue
                    newValue
                }
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>) {
                edit().putString(key ?: property.name, gson.toJson(value)).apply()
                if (cacheValue)
                    cache = value
            }
        }