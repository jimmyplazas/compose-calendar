package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Composable function that represents the body of a calendar.
 * It displays the days of a given month and allows interaction with them.
 *
 * @param <T> The type of the events in the calendar.
 * @param currentDate The current date to display the month for.
 * @param events A map of dates to lists of calendar events.
 * @param onDayClick A lambda function to be invoked when a day is clicked. It receives the clicked date and the list of events for that day.
 * @param calendarColors The colors to be used for styling the calendar.
 * @param isContentClickable A boolean indicating whether the content of the days is clickable.
 * @param firstDayOfWeek The first day of the week to be displayed in the calendar.
 * @see CalendarEvent
 */
@Composable
fun <T> CalendarBody(
    currentDate: LocalDate,
    events: Map<LocalDate, List<CalendarEvent<T>>>,
    onDayClick: (LocalDate, List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors,
    isContentClickable: Boolean,
    firstDayOfWeek: DayOfWeek
) {
    val days = remember(currentDate, firstDayOfWeek) {
        val firstDateOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeekOfFirstDayMonth = firstDateOfMonth.dayOfWeek
        val leadingEmptyDays = ((dayOfWeekOfFirstDayMonth.value - firstDayOfWeek.value + 7) % 7)

        List(leadingEmptyDays) { null } + List(currentDate.lengthOfMonth()) {
            LocalDate.of(currentDate.year, currentDate.monthValue, it + 1)
        }
    }

    BodyContent(days, events, isContentClickable, onDayClick, calendarColors)
}

/**
 * Composable function that represents the content of the calendar body.
 * It displays the days in a grid layout.
 *
 * @param <T> The type of the events in the calendar.
 * @param days The list of dates to display in the calendar. Null values represent empty cells.
 * @param events A map of dates to lists of calendar events.
 * @param isContentClickable A boolean indicating whether the content of the days is clickable.
 * @param onDayClick A lambda function to be invoked when a day is clicked.
 */
@Composable
private fun <T> BodyContent(
    days: List<LocalDate?>,
    events: Map<LocalDate, List<CalendarEvent<T>>>,
    isContentClickable: Boolean,
    onDayClick: (LocalDate, List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarColors.backgroundColor),
        horizontalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall)
    ) {
        itemsIndexed(
            items = days,
            key = { index: Int, dayData: LocalDate? -> dayData?.toString() ?: "emptyDay-$index" }
        ) { _, day ->
            CalendarDay(
                day = day,
                events = events[day] ?: emptyList(),
                isContentClickable = isContentClickable,
                onDayClick = { date, events -> onDayClick(date, events) },
                calendarColors = calendarColors
            )
        }
    }
}