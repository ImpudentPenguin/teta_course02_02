package me.emakeeva.teta_course02_02.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CalendarModel(
    val days: List<Int>,
    val month: String,
    val year: Int,
    val startIndex: Int,
    val activeDayState: MutableState<Int> = mutableStateOf(-1)
)