package org.reduxkotlin.thunk

import kotlinx.coroutines.runBlocking
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.compose
import org.reduxkotlin.createStore
import test.TestApp
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.test.Test
import kotlin.test.assertEquals

class ThunkTest {

    @Test
    fun asyncIncrement() {
        val store = createStore(
            TestApp.counterReducer,
            TestApp.TestState(),
            compose(
                applyMiddleware(createThunkMiddleware()),
            )
        )
        runBlocking {
            store.dispatch(TestApp.incrementThunk())
            // wait to assert to account for the last of thunk delays
            Timer().schedule(
                timerTask {
                    assertEquals(10000, store.state.counter)
                },
                50
            )
        }
    }
}
