package dev.alejo.compose_calendar

import androidx.compose.runtime.Composable
import java.time.LocalDate
/**
 * Represents a simple event associated with a specific date in the calendar.
 *
 * @property date The [LocalDate] of the event.
 * @property indicator An optional composable function that can be used to display
 *                     a visual indicator for the event on the calendar.
 *                     If null, no specific indicator will be shown.
 */
data class SimpleCalendarEvent(
    val date: LocalDate,
    val indicator: (@Composable () -> Unit)? = null
)