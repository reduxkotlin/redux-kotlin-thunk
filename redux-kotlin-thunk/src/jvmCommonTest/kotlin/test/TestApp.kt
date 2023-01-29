package test

import org.reduxkotlin.*
import org.reduxkotlin.thunk.Thunk
import java.util.*
import kotlin.concurrent.timerTask

object TestApp {
    sealed interface TestAction
    object Increment : TestAction

    data class TestState(val counter: Int = 0)

    val counterReducer = { state: TestState, action: Any ->
        when (action) {
            is Increment -> state.copy(counter = state.counter + 1)
            else -> state
        }
    }

    fun incrementThunk(): Thunk<TestState> = { dispatch, getState, _ ->
        Timer().schedule(
            timerTask {
                dispatch(Increment)
            },
            50
        )
        getState()
    }
}
