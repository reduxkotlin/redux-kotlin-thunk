# Redux-Kotlin

![Test](https://github.com/reduxkotlin/redux-kotlin-thunk/workflows/Test/badge.svg)

![badge][badge-android]
![badge][badge-native]
![badge][badge-js]
![badge][badge-jvm]
![badge][badge-linux]
![badge][badge-windows]
![badge][badge-mac]
![badge][badge-wasm]

A redux Thunk implementation for async action dispatch. 
A Thunk must conform to the `Thunk` typealias, which is a function with 3 paramaters: `dispatch`, `getState`, & `extraArg`.
A common use is to make a function return a `Thunk`.  This allows passing params to the function.

NOTE: Before v0.4.0 `Thunk` was an interface.  Kotlin 1.3.70 fixed a bug which allows using a typealias instead, which is more concise and closer to the JS implementation.

```
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

```
kotlin {
  sourceSets {
        commonMain { //   <---  name may vary on your project
            dependencies {
                implementation "org.reduxkotlin:redux-kotlin-thunk:0.5.3"
            }
        }
 }
```

For JVM only:
```
  implementation "org.reduxkotlin:redux-kotlin-jvm-thunk:0.5.3"
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
[badge-wasm]: https://img.shields.io/badge/platform-wasm-darkblue.svg?style=flat
