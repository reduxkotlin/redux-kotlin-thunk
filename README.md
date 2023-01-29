# Redux-Kotlin-Thunk

[![Release](https://github.com/reduxkotlin/redux-kotlin-thunk/actions/workflows/release.yml/badge.svg)](https://github.com/reduxkotlin/redux-kotlin-thunk/actions/workflows/release.yml)
![badge][badge-android]
![badge][badge-native]
![badge][badge-js]
![badge][badge-jvm]
![badge][badge-linux]
![badge][badge-windows]
![badge][badge-mac]
[![Slack chat](https://img.shields.io/badge/kotlinlang-%23redux-green?logo=slack&style=flat-square)][slack]
[![Dokka docs](https://img.shields.io/badge/docs-dokka-orange?style=flat-square&logo=kotlin)](http://reduxkotlin.github.io/redux-kotlin-thunk)
[![Version maven-central](https://img.shields.io/maven-central/v/org.reduxkotlin/redux-kotlin-thunk?logo=apache-maven&style=flat-square)](https://mvnrepository.com/artifact/org.reduxkotlin/redux-kotlin-thunk/latest)
[![Version maven-snapshot](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Foss.sonatype.org%2Fcontent%2Frepositories%2Fsnapshots%2Forg%2Freduxkotlin%2Fredux-kotlin-thunk%2Fmaven-metadata.xml&logo=apache-maven&label=maven-snapshot&style=flat-square)](https://oss.sonatype.org/content/repositories/snapshots/org/reduxkotlin/redux-kotlin-thunk/)

A redux Thunk implementation for async action dispatch. 
A Thunk must conform to the `Thunk` typealias, which is a function with 3 paramaters: `dispatch`, `getState`, & `extraArg`.
A common use is to make a function return a `Thunk`.  This allows passing params to the function.

NOTE: Before v0.4.0 `Thunk` was an interface.  Kotlin 1.3.70 fixed a bug which allows using a typealias instead, which is more concise and closer to the JS implementation.

```kotlin
val store = createStore(::reducer, applymiddleware(createThunkMiddleware()))

...

fun fooThunk(query: String): Thunk<AppState> = { dispatch, getState, extraArg ->
    dispatch(FetchingFooAction)
    launch {
        val result = api.foo(query)
        if (result.isSuccessful()) {
            dispatch(FetchFooSuccess(result.payload)
        } else {
            dispatch(FetchFooFailure(result.message)
        }
    }  
}

...

fun bar() {
   dispatch(fooThunk("my query")) 
}
```

__How to add to project:__

Artifacts are hosted on maven central.  For multiplatform, add the following to your shared module:

```kotlin
kotlin {
  sourceSets {
        commonMain { //   <---  name may vary on your project
            dependencies {
                implementation("org.reduxkotlin:redux-kotlin-thunk:_")
            }
        }
 }
```

For JVM only:
```kotlin
implementation("org.reduxkotlin:redux-kotlin-thunk-jvm:_")
```

[badge-android]: http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat
[badge-native]: http://img.shields.io/badge/platform-native-lightgrey.svg?style=flat	
[badge-native]: http://img.shields.io/badge/platform-native-lightgrey.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat
[badge-linux]: http://img.shields.io/badge/platform-linux-important.svg?style=flat
[badge-linux]: http://img.shields.io/badge/platform-linux-important.svg?style=flat 
[badge-windows]: http://img.shields.io/badge/platform-windows-informational.svg?style=flat
[badge-windows]: http://img.shields.io/badge/platform-windows-informational.svg?style=flat
[badge-mac]: http://img.shields.io/badge/platform-macos-lightgrey.svg?style=flat
[badge-mac]: http://img.shields.io/badge/platform-macos-lightgrey.svg?style=flat
[slack]: https://kotlinlang.slack.com/archives/C8A8G5F9Q
