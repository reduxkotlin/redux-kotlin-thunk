package org.reduxkotlin

typealias Thunk = (Dispatcher, GetState, Any?) -> Any
fun createThunkMiddleware(extraArgument: Any? = null): Middleware =
    { store ->
        { next: Dispatcher ->
            { action: Any ->
                if (action is Function<*>) {
                    try {
                        (action as Thunk)(store.dispatch, store.getState, extraArgument)
                    } catch (e: Exception) {
//                    Logger.d("Dispatching functions must use type Thunk: " + e.message)
                    }
                } else {
                    next(action)
                }
            }
        }
    }

val thunk = createThunkMiddleware()
