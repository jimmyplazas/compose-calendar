package dev.alejo.compose_calendar

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.datetime.LocalDate

/**
 * Represents an event to be displayed on a specific calendar date.
 *
 * @param title Optional title of the event. Can be `null` if not applicable.
 * @param description Optional detailed description of the event.
 * @param date The [LocalDate] on which the event occurs. This field is required.
 * @param icon Optional [ImageVector] to visually represent the event.
 */
data class CalendarEvent(
    val title: String? = null,
    val description: String? = null,
    val date: LocalDate,
    val icon: ImageVector? = null
)