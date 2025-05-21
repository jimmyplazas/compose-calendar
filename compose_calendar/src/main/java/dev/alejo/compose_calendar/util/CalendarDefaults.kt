package dev.alejo.compose_calendar.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * This object provides default values for various components of the calendar.
 * It includes default dimensions for spacing, sizes, and default colors for different parts of the calendar.
 */
object CalendarDefaults {
    /**
     * This object holds the dimension values used across the application.
     * Using this object ensures consistency in the application's UI and makesit easier to
     * manage dimensions.
     */
    data object Dimens {
        val EventsSpacedBy = 2.dp
        val XSmall = 4.dp
        val Small = 8.dp
        val Default = 16.dp
        val IconSize = 16.dp
        val DaySize = 48.dp
    }

    /**
     * This function provides default color values based on the `MaterialTheme.colorScheme` to style
     * various parts of a calendar, such as the background, content, headers, navigation, and events.
     * You can override any of these default colors by passing custom colors as arguments.
     *
     * @param backgroundColor The background color for the calendar body. Defaults to `MaterialTheme.colorScheme.surface`.
     * @param contentColor The color used for the main content of the calendar. Defaults to `MaterialTheme.colorScheme.onSurface`.
     * @param headerBackgroundColor The background color for the calendar header. Defaults to `MaterialTheme.colorScheme.surface`.
     * @param headerContentColor The color used for the content in the calendar header. Defaults to `MaterialTheme.colorScheme.onSurface`.
     * @param navigationBackgroundColor The background color for navigation elements. Defaults to `MaterialTheme.colorScheme.primary`.
     * @param navigationContentColor The color used for the content of navigation elements . Defaults to `MaterialTheme.colorScheme.onPrimary`.
     * @param eventBackgroundColor The background color for event cells in the calendar. Defaults to `MaterialTheme.colorScheme.surfaceContainerLow`.
     * @param eventContentColor The color used for the text or content in event cells. Defaults to `MaterialTheme.colorScheme.onSurface`.
     *
     * @return A [CalendarColors] object that holds the specified or default colors for styling the calendar.
     */
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

/**
 * This immutable data class defines the color properties for various visual elements of the calendar,
 * allowing customization of the background, content, headers, navigation, and event cells.
 *
 * @property backgroundColor The background color of the calendar.
 * @property contentColor The color used for the main content of the calendar, such as text or icons.
 * @property headerBackgroundColor The background color of the calendar header.
 * @property headerContentColor The color of the text or content displayed in the calendar header.
 * @property navigationBackgroundColor The background color of navigation elements, such as next/previous buttons.
 * @property navigationContentColor The color of the content in navigation elements, like icons or arrows.
 * @property eventBackgroundColor The background color of individual event cells in the calendar.
 * @property eventContentColor The color of the content within event cells.
 */
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
