package dev.alejo.compose_calendar.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

object CalendarDefaults {

    @Composable
    fun calendarColors(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        headerBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        headerContentColor: Color = MaterialTheme.colorScheme.onSurface,
        navigationBackgroundColor: Color = MaterialTheme.colorScheme.primary,
        navigationContentColor: Color = MaterialTheme.colorScheme.onPrimary,
        eventBackgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
        eventContentColor: Color = MaterialTheme.colorScheme.onSurface,
    ): CalendarColors = CalendarColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        headerBackgroundColor = headerBackgroundColor,
        headerContentColor = headerContentColor,
        navigationBackgroundColor = navigationBackgroundColor,
        navigationContentColor = navigationContentColor,
        eventBackgroundColor = eventBackgroundColor,
        eventContentColor = eventContentColor
    )

}

@Immutable
data class CalendarColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val headerBackgroundColor: Color,
    val headerContentColor: Color,
    val navigationBackgroundColor: Color,
    val navigationContentColor: Color,
    val eventBackgroundColor: Color,
    val eventContentColor: Color
)
