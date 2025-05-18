package dev.alejo.compose_calendar

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.datetime.LocalDate

data class CalendarEvent(
    val title: String,
    val description: String? = null,
    val date: LocalDate,
    val icon: ImageVector
)