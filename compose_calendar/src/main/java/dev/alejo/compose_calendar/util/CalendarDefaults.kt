package dev.alejo.compose_calendar.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Contains default configuration values and utility classes used throughout the calendar component.
 *
 * The `CalendarDefaults` object centralizes commonly used settings to ensure consistency and
 * simplify customization. It includes predefined limits for event indicators, layout options,
 * standard dimensions, and a utility function for color theming.
 *
 * Contents:
 * - [IndicatorLimit]: Defines how many event indicators to show per calendar day.
 * - [IndicatorLayout]: Specifies the layout style for arranging indicators.
 * - [Dimens]: Provides standard dimensions for consistent UI spacing and sizing.
 * - [calendarColors]: Returns default or customized colors for the calendar UI.
 */

object CalendarDefaults {

    /**
     * Defines the maximum number of event indicators to display within a calendar day cell.
     *
     * This sealed class allows the developer to control how many indicators should be shown
     * before truncating or summarizing the remaining events.
     *
     * - [One]: Show only one indicator.
     * - [Two]: Show up to two indicators.
     * - [Three]: Show up to three indicators.
     * - [Four]: Show up to four indicators.
     *
     * @property maxCount The maximum number of indicators to render.
     */
    sealed class IndicatorLimit(val maxCount: Int) {
        data object One : IndicatorLimit(1)
        data object Two : IndicatorLimit(2)
        data object Three : IndicatorLimit(3)
        data object Four : IndicatorLimit(4)
    }

    /**
     * Represents the layout style used to arrange event indicators within a calendar day cell.
     *
     * This sealed class defines the possible visual arrangements that determine how the indicators
     * are displayed when there are multiple events on a single day.
     *
     * - [Column]: Displays the indicators vertically in a single column.
     * - [Row]: Displays the indicators horizontally in a single row.
     * - [Grid]: Displays the indicators in a 2x2 grid layout.
     */
    sealed class IndicatorLayout {
        data object Column : IndicatorLayout()
        data object Row : IndicatorLayout()
        data object Grid : IndicatorLayout()
    }

    /**
     * This object holds the dimension values used across the application.
     * Using this object ensures consistency in the application's UI and makesit easier to
     * manage dimensions.
     */
    data object Dimens {
        val None = 0.dp
        val EventsSpacedBy = 2.dp
        val XSmall = 4.dp
        val Small = 8.dp
        val Default = 16.dp
        val ButtonSize = 44.dp
        val EventIndicatorContainerSize = 32.dp
        val DaySize = 48.dp
    }

    /**
     * Provides default color values for customizing the appearance of the calendar UI.
     *
     * Based on the current `MaterialTheme.colorScheme`, this function returns a [CalendarColors]
     * instance that defines the color roles for various calendar sections. Developers can override
     * individual color values to tailor the look and feel of the calendar.
     *
     * @param backgroundColor The background color for the calendar grid.
     * @param contentColor The color for general text and UI elements.
     * @param headerBackgroundColor The background color for the header section.
     * @param headerContentColor The color of text/icons in the header.
     * @param navigationContainerColor The background color for navigation controls.
     * @param navigationContentColor The color of icons/text in navigation controls.
     * @param navigationDisableContainerColor The background for disabled navigation buttons.
     * @param navigationDisableContentColor The content color for disabled navigation buttons.
     * @param eventBackgroundColor The background color for cells with events.
     * @param eventContentColor The text/icon color for cells with events.
     *
     * @return A [CalendarColors] object containing the specified or default color values.
     */

    @Composable
    fun calendarColors(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = MaterialTheme.colorScheme.onSurface,
        headerBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        headerContentColor: Color = MaterialTheme.colorScheme.onSurface,
        navigationContainerColor: Color = MaterialTheme.colorScheme.primary,
        navigationContentColor: Color = MaterialTheme.colorScheme.onPrimary,
        navigationDisableContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        navigationDisableContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        eventBackgroundColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
        eventContentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    ): CalendarColors = CalendarColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        headerBackgroundColor = headerBackgroundColor,
        headerContentColor = headerContentColor,
        navigationContainerColor = navigationContainerColor,
        navigationContentColor = navigationContentColor,
        navigationDisableContainerColor = navigationDisableContainerColor,
        navigationDisableContentColor = navigationDisableContentColor,
        eventBackgroundColor = eventBackgroundColor,
        eventContentColor = eventContentColor
    )
}

/**
 * Represents the set of colors used to style various parts of the calendar component.
 *
 * This immutable data class centralizes all color properties needed for consistent theming
 * across the calendar UI, including background colors, content colors, headers, navigation,
 * disabled states, and event indicators.
 *
 * @property backgroundColor The main background color of the calendar body.
 * @property contentColor The primary color for the main content displayed in the calendar.
 * @property headerBackgroundColor The background color of the calendar header.
 * @property headerContentColor The color used for content inside the calendar header.
 * @property navigationContainerColor The background color of the navigation controls (e.g., month selector).
 * @property navigationContentColor The color for text/icons inside the navigation controls.
 * @property navigationDisableContainerColor The background color used for disabled navigation elements.
 * @property navigationDisableContentColor The color used for disabled navigation content (text/icons).
 * @property eventBackgroundColor The background color for event indicators or event cells.
 * @property eventContentColor The color used for event indicator content such as text or icons.
 */
@Immutable
data class CalendarColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val headerBackgroundColor: Color,
    val headerContentColor: Color,
    val navigationContainerColor: Color,
    val navigationContentColor: Color,
    val navigationDisableContainerColor: Color,
    val navigationDisableContentColor: Color,
    val eventBackgroundColor: Color,
    val eventContentColor: Color
)
