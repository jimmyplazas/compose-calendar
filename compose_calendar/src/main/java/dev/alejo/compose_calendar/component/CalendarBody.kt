package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import java.time.LocalDate

/**
 * Composable that displays the body of a calendar using a 7-column grid layout.
 *
 * This layout shows each day of the current month view, including empty cells for padding
 * based on the start of the week. Each day is rendered using [CalendarDay], which includes
 * support for displaying event indicators and handling day clicks.
 *
 * @param T The generic type associated with events.
 * @param events A map of [LocalDate] to a list of [CalendarEvent]s that are associated with each date.
 * @param days A list of [LocalDate]s (nullable) representing the visible days in the current month grid.
 *             Null values represent empty cells for proper alignment.
 * @param onDayClick Callback triggered when a day is clicked. Receives the date and its corresponding events.
 * @param eventIndicator Optional composable lambda to visually represent an event indicator for a given event.
 *                       Receives the event, its position, and the total number of events.
 * @param maxIndicators Defines the maximum number of indicators to be shown per day.
 * @param indicatorLayout Specifies the layout style (Column, Row, or Grid) for the event indicators.
 * @param calendarColors Contains color definitions for the calendar's appearance.
 * @param isContentClickable Whether each calendar day is clickable or not.
 */

@Composable
fun <T> CalendarBody(
    events: Map<LocalDate, List<CalendarEvent<T>>>,
    days: List<LocalDate?>,
    onDayClick: (LocalDate, List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    eventIndicator: (@Composable (event: CalendarEvent<T>, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit,
    indicatorLayout: CalendarDefaults.IndicatorLayout,
    calendarColors: CalendarColors,
    isContentClickable: Boolean
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
                eventIndicator = eventIndicator,
                maxIndicators = maxIndicators,
                indicatorLayout = indicatorLayout,
                isContentClickable = isContentClickable,
                onDayClick = { date, events -> onDayClick(date, events) },
                calendarColors = calendarColors
            )
        }
    }
}