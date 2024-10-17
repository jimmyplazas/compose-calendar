package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.CalendarDefaults.calendarColors
import java.time.LocalDate

@Composable
fun CalendarBody(
    date: LocalDate,
    events: List<CalendarEvent>,
    onDayClick: (CalendarEvent?) -> Unit = {},
    calendarColors: CalendarColors
) {
    val startDay = date.withDayOfMonth(1)
    val daysInMonth = startDay.lengthOfMonth()
    val startDayOfWeek = (startDay.dayOfWeek.value - 1) % 7

    // List of days including leading empty days
    val days = List(startDayOfWeek) { "" } + List(daysInMonth) { it + 1 }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
            .background(calendarColors.backgroundColor),
        horizontalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall)
    ) {
        items(days) { day ->
            CalendarDay(
                day = day,
                events = events,
                currentDate = date,
                onDayClick = { event -> onDayClick(event) },
                calendarColors = calendarColors
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    CalendarBody(date = LocalDate.now(), events = emptyList(), calendarColors = calendarColors())
}