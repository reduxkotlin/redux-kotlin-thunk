package org.reduxkotlin


typealias Thunk = (Dispatcher) -> Any


fun createThunkMiddleware(store: Store)=
    { dispatch: Dispatcher ->
        { action: Any ->
            if (action is Function<*>) {
                try {
                    (action as Thunk)(dispatch)
                } catch (e: Exception) {
//                    Logger.d("Dispatching functions must use type Thunk: " + e.message)
                }
            } else {
                dispatch(action)
            }
        }
    }

val thunk = ::createThunkMiddleware
