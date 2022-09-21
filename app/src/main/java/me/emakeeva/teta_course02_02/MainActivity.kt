package me.emakeeva.teta_course02_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import me.emakeeva.teta_course02_02.model.MainState
import me.emakeeva.teta_course02_02.ui.theme.Teta_course02_02Theme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Teta_course02_02Theme {
                when (val state = viewModel.state.collectAsState().value) {
                    is MainState.Calendar -> Calendar(
                        calendar = state.calendar,
                        onClick = viewModel::onClick
                    )
                    is MainState.Schedule -> Schedule(
                        day = state.calendar.activeDayState.value,
                        state.schedule
                    )
                }
            }
        }
    }
}