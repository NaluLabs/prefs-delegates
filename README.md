# Prefs delegates
Kotlin delegates for Android Shared Preferences

[![](https://jitpack.io/v/nalulabs/prefs-delegates.svg)](https://jitpack.io/#nalulabs/prefs-delegates)

This project contains three modules:
 * prefs-delegates: core module to use kotlin delegates for primitive types
 * prefs-delegates-gson: support for objects serialization using gson
 * fake-prefs: fake class to simplify testing

This library contains some extensions methods on `SharedPreferences` class, there are no classes to extend.
It can be used in a class that contains a `SharedPreferences` constructor parameter:

```kotlin
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
```

When `count++` is invoked the value from the shared preferences is read and the incremented value is saved, 
under the hood something like this is executed:

```kotlin
prefs.edit().putInt("count", prefs.getInt("count", 0) + 1).apply()
```

More info about are available in this
[Medium post](https://hackernoon.com/kotlin-delegates-in-android-development-part-1-50346cf4aed7).

## JitPack configuration

Prefs-delegates is available on [JitPack](https://jitpack.io/#nalulabs/prefs-delegates),
add the JitPack repository in your build.gradle (in top level dir):
```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
```
and the dependency in the build.gradle of the module:

```gradle
dependencies {
    //main lib
    compile 'com.github.nalulabs.prefs-delegates:prefs-delegates:0.1'
    //support for objects serialization using gson
    compile 'com.github.nalulabs.prefs-delegates:prefs-delegates-gson:0.1'

    //fake class to simplify testing
    testCompile 'com.github.nalulabs.prefs-delegates:fake-prefs:0.1'
    //and/or
    androidTestCompile 'com.github.nalulabs.prefs-delegates:fake-prefs:0.1'
}
```

## License

    Copyright 2017 Fabio Collini

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
