package org.reduxkotlin

/**
 * Thunk middleware for async action dispatches.
 * Usage:
 *    val store = createStore(myReducer, initialState,
 *          applyMiddleware(thunk, myMiddleware))
 *
 *    fun myNetworkThunk(query: String): Thunk<AppState> = { dispatch, getState, extraArgument ->
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
typealias Thunk<State> = (dispatch: Dispatcher, getState: GetState<State>, extraArg: Any?) -> Any
typealias ThunkMiddleware<State> = Middleware<State>

fun <State> ThunkMiddleware<State>.withExtraArgument(arg: Any?) = createThunkMiddleware<State>(arg)

fun <State> createThunkMiddleware(extraArgument: Any? = null): ThunkMiddleware<State> =
    { store ->
        { next: Dispatcher ->
            { action: Any ->
                if (action is Function<*>) {
                    try {
                        (action as Thunk<*>)(store.dispatch, store.getState, extraArgument)
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
