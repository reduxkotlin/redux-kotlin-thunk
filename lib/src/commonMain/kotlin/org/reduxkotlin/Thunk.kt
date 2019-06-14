package org.reduxkotlin

typealias Thunk = (Dispatcher, GetState, Any?) -> Any
typealias ThunkMiddleware = Middleware
fun createThunkMiddleware(extraArgument: Any? = null): ThunkMiddleware =
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

fun ThunkMiddleware.withExtraArgument(arg: Any?) = createThunkMiddleware(arg)
