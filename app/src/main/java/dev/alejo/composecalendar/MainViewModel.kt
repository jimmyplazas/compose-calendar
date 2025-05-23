package dev.alejo.composecalendar

import androidx.lifecycle.ViewModel
import dev.alejo.compose_calendar.CalendarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.asStateFlow()

    init {
        _state.value = CalendarState(
            events = getListOfEvents(),
            simpleEvents = getListOfEvents().map { it.date }
        )
    }

    private fun getListOfEvents() = listOf(
        CalendarEvent(
            data = MyData("Event 1"),
            date = LocalDate.now()
        ),
        CalendarEvent(
            data = MyData("Event 1"),
            date = LocalDate.now().minusDays(20)
        ),
        CalendarEvent(
            data = MyData("Event 2"),
            date = LocalDate.now().minusDays(20)
        ),
        CalendarEvent(
            data = MyData("Event 1"),
            date = LocalDate.now().plusDays(1)
        ),
        CalendarEvent(
            data = MyData("Event 2"),
            date = LocalDate.now().plusDays(1)
        ),
        CalendarEvent(
            data = MyData("Event 3"),
            date = LocalDate.now().plusDays(1)
        ),
        CalendarEvent(
            data = MyData("Event 4"),
            date = LocalDate.now().plusDays(1)
        ),
        CalendarEvent(
            data = MyData("Event 3"),
            date = LocalDate.now().plusDays(4)
        ),
        CalendarEvent(
            data = MyData("Event 4"),
            date = LocalDate.now().plusDays(4)
        ),
        CalendarEvent(
            data = MyData("Event 4"),
            date = LocalDate.now().plusDays(4)
        ),
    )

}