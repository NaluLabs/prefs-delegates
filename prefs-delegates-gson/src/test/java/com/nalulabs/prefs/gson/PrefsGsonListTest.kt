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

import com.nalulabs.prefs.fake.FakeSharedPreferences
import org.assertj.core.api.Assertions
import org.junit.Test

class PrefsGsonListTest {

    val prefs = FakeSharedPreferences()

    var person by prefs.jsonList<Person?>()

    var personCached by prefs.jsonList<Person>(cacheValue = true)

    @Test
    fun saveValue() {
        person = listOf(Person("a", "b"))

        Assertions.assertThat(prefs.getString("person", "")).isEqualTo("""[{"name":"a","surname":"b"}]""")
    }

    @Test
    fun appendValue() {
        person = listOf(Person("a", "b"))
        person += Person("c", "d")

        Assertions.assertThat(prefs.getString("person", "")).isEqualTo("""[{"name":"a","surname":"b"},{"name":"c","surname":"d"}]""")
    }

    @Test
    fun loadValue() {
        prefs.edit().putString("person", """[{"name":"a","surname":"b"}]""").apply()

        Assertions.assertThat(person).containsExactly(Person("a", "b"))
    }

    @Test
    fun cacheValue() {
        personCached = listOf(Person("a", "b"))

        prefs.edit().putString("person", "abc").apply()

        Assertions.assertThat(personCached).containsExactly(Person("a", "b"))
    }
}