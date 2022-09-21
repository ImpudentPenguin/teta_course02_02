package me.emakeeva.teta_course02_02.model

sealed class MainState {
    class Calendar(val calendar: CalendarModel) : MainState()
    class Schedule(val calendar: CalendarModel, val schedule: List<String>) : MainState()
}