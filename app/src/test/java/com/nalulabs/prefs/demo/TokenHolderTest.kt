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

package com.nalulabs.prefs.demo

import com.nalulabs.prefs.fake.FakeSharedPreferences
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TokenHolderTest {
    @Test
    fun shouldCount() {
        val prefs = FakeSharedPreferences()
        val tokenHolder = TokenHolder(prefs)

        tokenHolder.saveToken("a")
        tokenHolder.saveToken("b")

        assertThat(tokenHolder.count).isEqualTo(2)
        assertThat(prefs.getInt("count", 0)).isEqualTo(2)
    }
}