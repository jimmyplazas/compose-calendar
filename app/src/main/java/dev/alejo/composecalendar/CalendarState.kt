package dev.alejo.composecalendar

import dev.alejo.compose_calendar.CalendarEvent
import java.time.LocalDate

data class CalendarState(
    val events: List<CalendarEvent<MyData>> = emptyList(),
    val simpleEvents: List<LocalDate> = emptyList()
)