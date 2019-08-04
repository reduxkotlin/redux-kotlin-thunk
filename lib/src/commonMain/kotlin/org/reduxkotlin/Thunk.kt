package org.reduxkotlin

typealias Thunk = (Dispatcher, GetState, Any?) -> Any
typealias ThunkMiddleware = Middleware
fun createThunkMiddleware(extraArgument: Any? = null): ThunkMiddleware =
    { store ->
        { next: Dispatcher ->
            { action: Any ->
                try {
                    (action as Thunk)(store.dispatch, store.getState, extraArgument)
                } catch (e: ClassCastException) {
                    next(action)
                } catch (e: Exception) {
                    throw IllegalArgumentException()
//                    Logger.d("Dispatching functions must use type Thunk: " + e.message)
                }
            }
        }
    }

val thunk = createThunkMiddleware()

fun ThunkMiddleware.withExtraArgument(arg: Any?) = createThunkMiddleware(arg)
