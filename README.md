# Redux-Kotlin

[![CircleCI](https://circleci.com/gh/reduxkotlin/redux-kotlin-thunk.svg?style=svg)](https://circleci.com/gh/reduxkotlin/redux-kotlin-thunk)


![badge][badge-android]
![badge][badge-native]
![badge][badge-js]
![badge][badge-jvm]
![badge][badge-linux]
![badge][badge-windows]
![badge][badge-mac]
![badge][badge-wasm]

A redux Thunk implementation for async action dispatch. 
Thunk implement must implement the `Thunk` interface, which only has one dispatch method.
A more friendly way to create a thunk is with the `createThunk` function.  Both are illustrated below:

```
    val store = createStore(::reducer, applymiddleware(createThunkMiddleware()))
    
    ...
    
    class FooThunk: Thunk<State> {
    
        override fun dispatch(dispatch: Dispatcher, getState: GetState<State>, extraArg: Any?) {
            dispatch(FetchingFooAction)
            launch {
                val result = api.foo()
                if (result.isSuccessful()) {
                    dispatch(FetchFooSuccess(result.payload)
                } else {
                    dispatch(FetchFooFailure(result.message)
                } 
            } 
        }
    }
    
    val fetchBar = createThunk<State> {dispatch, getState, extraArgument -> 
        dispatch(FetchingBarAction)
        launch {
            val result = api.bar()
            if (result.isSuccessful()) {
                dispatch(FetchBarSuccess(result.payload)
            } else {
                dispatch(FetchBarFailure(result.message)
            } 
        } 
    
    ...
    
    fun bar() {
       dispatch(FooThunk()::dispatch) 
       dispatch(fetchBar)
    }
```

__How to add to project:__

Artifacts are hosted on maven central.  For multiplatform, add the following to your shared module:

```
kotlin {
  sourceSets {
        commonMain { //   <---  name may vary on your project
            dependencies {
                implementation "org.reduxkotlin:redux-kotlin-thunk:0.2.8"
            }
        }
 }
```

For JVM only:
```
  implementation "org.reduxkotlin:redux-kotlin-jvm-thunk:0.2.8"
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
