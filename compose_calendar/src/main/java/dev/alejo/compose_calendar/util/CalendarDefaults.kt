package dev.alejo.compose_calendar.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

object CalendarDefaults {

    @Composable
    fun CalendarColors(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        headerBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        headerContentColor: Color = MaterialTheme.colorScheme.onSurface,
        navigationBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        navigationContentColor: Color = MaterialTheme.colorScheme.onSurface
    ): CalendarColors {
        return CalendarColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            headerBackgroundColor = headerBackgroundColor,
            headerContentColor = headerContentColor,
            navigationBackgroundColor = navigationBackgroundColor,
            navigationContentColor = navigationContentColor
        )
    }

}

@Immutable
data class CalendarColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val headerBackgroundColor: Color,
    val headerContentColor: Color,
    val navigationBackgroundColor: Color,
    val navigationContentColor: Color
)
