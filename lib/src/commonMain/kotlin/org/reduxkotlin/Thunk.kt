package org.reduxkotlin

/**
 * Thunk middleware for async action dispatches.
 * Usage:
 *    val store = createStore(myReducer, initialState,
 *          applyMiddleware(thunk, myMiddleware))
 *
 *    val myNetworkThunk(query: String) = createThunk { dispatch, getState, extraArgument ->
 *          launch {
 *              dispatch(LoadingAction())
 *              //do async stuff
 *              val result = api.fetch(query)
 *              dispatch(CompleteAction(result))
 *          }
 *      }
 *
 *    store.dispatch(myNetworkThunk("query"))
 */
fun createThunkMiddleware(extraArgument: Any? = null): ThunkMiddleware =
    { store ->
        { next: Dispatcher ->
            { action: Any ->
                if (action is Thunk) {
                    try {
                        action.dispatch(store.dispatch, store.getState, extraArgument)
                    } catch (e: Exception) {
//                        Logger.d("Dispatching functions must use type Thunk: " + e.message)
                        throw IllegalArgumentException()
                    }
                } else {
                    next(action)
                }
            }
        }
    }

typealias ThunkMiddleware = Middleware
val thunk = createThunkMiddleware()

fun ThunkMiddleware.withExtraArgument(arg: Any?) = createThunkMiddleware(arg)

/**
 * Interface that can be dispatched and handled by ThunkMiddleware.
 * Asynchronous operations and Actions may be dispatched from within a Thunk.
 * Due to limitation of K/N a type alias does not work currently.
 */
interface Thunk {
    fun dispatch(dispatch: Dispatcher, getState: GetState, extraArgument: Any?): Any
}
/**
 * Convenience function for creating thunks.
 * Usage:
 *      val myThunk = createThunk { dispatch, getState, extraArgument ->
 *              //do async stuff
 *              dispatch(NewAction())
 *              }
 *
 *      ....
 *
 *      store.dispatch(myThunk)
 *
 * DEV NOTE:  This will not be needed if/when typealias for Thunk works with K/N
 */
fun createThunk(thunkLambda: (dispatch: Dispatcher, getState: GetState, extraArgument: Any?) -> Any): Thunk {
    return object : Thunk {
        override fun dispatch(dispatch: Dispatcher, getState: GetState, extraArgument: Any?): Any =
            thunkLambda(dispatch, getState, extraArgument)
    }
}

/*
 * Unable to use Thunk as a typealias currently do to a limitation of kotlin native.
 * Leaving code here as a reference to revisit.
 * /
typealias Thunk = (Dispatcher, GetState, Any?) -> Any
fun createThunkMiddleware(extraArgument: Any? = null): ThunkMiddleware =
    { store ->
        { next: Dispatcher ->
            { action: Any ->
                if (action is Function<*>) {
                    try {
                        (action as Thunk)(store.dispatch, store.getState, extraArgument)
                    } catch (e: Exception) {
                        throw IllegalArgumentException()
//                    Logger.d("Dispatching functions must use type Thunk: " + e.message)
                    }
                } else {
                    next(action)
                }
            }
        }
    }
 */


