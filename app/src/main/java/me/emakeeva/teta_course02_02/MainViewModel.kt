package me.emakeeva.teta_course02_02

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.emakeeva.teta_course02_02.model.CalendarModel
import me.emakeeva.teta_course02_02.model.MainState
import me.emakeeva.teta_course02_02.utils.month
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val calendar = CalendarModel(
        days = (1..31).toList(),
        month = 8.month(),
        year = 2022,
        startIndex = Random.nextInt(0, 7)
    )
    // TODO use real data
    private val schedule = listOf(
        "00: 00 Спать",
        "01: 00 Спать",
        "02: 00 Спать",
        "03: 00 Спать",
        "04: 00 Спать",
        "05: 00 Спать",
        "06: 00 Спать",
        "07: 00 Подъем",
        "07: 30 Еще полчаса поваляться",
        "08: 00 Еще пять минуточек",
        "08: 05 ну еще десять минуточек",
        "08: 15 Душ",
        "09: 00 Завтрак",
        "10: 00 Работа",
        "11: 00 Работа",
        "12: 00 Работа",
        "13: 00 А у нас будет что-то типо обЭда?",
        "14: 00 Работа",
        "15: 00 Работа",
        "16: 00 Работа",
        "17: 00 Работа",
        "18: 00 Работа",
        "19: 00 Работа",
        "20: 00 ДЗ",
        "21: 00 Поиграть с друзьями",
        "23: 00 Посмотреть одну серию",
        "23: 30 Посмотреть еще одну серию",
        "23: 50 Спать"
    )
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.Calendar(calendar))
    val state = _state.asStateFlow()

    fun onClick() {
        if (_state.value is MainState.Calendar) {
            _state.value = MainState.Schedule(calendar, schedule)
        } else {
            _state.value = MainState.Calendar(calendar)
        }
    }
}