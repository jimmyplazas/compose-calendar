package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.CalendarDefaults.Dimens
import java.time.LocalDate

/**
 * Composable that visually represents a single day in the calendar.
 *
 * Displays the day number and, if events are present, shows a customizable set of event indicators.
 * The appearance of indicators (limit and layout) is controlled via parameters.
 * Background and text colors adapt based on whether the day has events.
 * The day can also be made clickable to support user interactions.
 *
 * @param T Generic type associated with each calendar event.
 * @param day The date represented by this calendar cell. Can be null to represent an empty/padded cell.
 * @param events List of events associated with this date.
 * @param eventIndicator Composable lambda that renders each individual event indicator.
 * It receives the event, its index in the displayed list, and the total number of events.
 * @param maxIndicators Specifies the maximum number of event indicators shown, using [CalendarDefaults.IndicatorLimit].
 * @param indicatorLayout Defines the layout of the indicators: column, row, or grid, using [CalendarDefaults.IndicatorLayout].
 * @param isContentClickable Whether the day cell is clickable. If true, it will trigger the [onDayClick] callback.
 * @param onDayClick Callback triggered when the day is clicked, providing the date and its associated events.
 * @param calendarColors Color configuration for the background and content, defined via [CalendarColors].
 */

@Composable
fun <T> CalendarDay(
    day: LocalDate?,
    events: List<CalendarEvent<T>>,
    eventIndicator: (@Composable (event: CalendarEvent<T>, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit,
    indicatorLayout: CalendarDefaults.IndicatorLayout,
    isContentClickable: Boolean,
    onDayClick: (date: LocalDate, events: List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors
) {
    day?.let { date ->
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = Dimens.DaySize, minHeight = Dimens.DaySize)
                .clip(RoundedCornerShape(Dimens.Small))
                .background(
                    color = if (events.isNotEmpty()) {
                        calendarColors.eventBackgroundColor
                    } else {
                        calendarColors.backgroundColor
                    },
                    shape = RoundedCornerShape(Dimens.Small)
                )
                .then(
                    if (isContentClickable) {
                        Modifier.clickable { onDayClick(date, events) }
                    } else {
                        Modifier
                    }
                )
                .padding(Dimens.XSmall),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    color = if (events.isNotEmpty()) {
                        calendarColors.eventContentColor
                    } else {
                        calendarColors.contentColor
                    }
                )
                if (events.isNotEmpty()) {
                    EventIcon(events, eventIndicator, maxIndicators, indicatorLayout)
                }
            }
        }
    }
}

/**
 * Composable that displays visual indicators for events on a calendar day.
 *
 * It renders up to a defined number of indicators based on the given layout: column, row, or grid.
 * The indicator content is defined by a composable lambda provided by the user, allowing full customization.
 *
 * @param T Generic type associated with each calendar event.
 * @param events List of events associated with the day.
 * @param eventIndicator Composable lambda used to render each event indicator.
 * It receives the event, its position among visible indicators, and the total number of events.
 * @param limit Defines the maximum number of indicators to display, via [CalendarDefaults.IndicatorLimit].
 * @param layout Defines how the indicators are arranged: vertically, horizontally, or in a grid,
 * using [CalendarDefaults.IndicatorLayout].
 */

@Composable
fun <T> EventIcon(
    events: List<CalendarEvent<T>>,
    eventIndicator: (@Composable (event: CalendarEvent<T>, position: Int, count: Int) -> Unit)?,
    limit: CalendarDefaults.IndicatorLimit,
    layout: CalendarDefaults.IndicatorLayout,
) {
    eventIndicator?.let { indicator ->
        val itemsToShow = events.take(limit.maxCount)
        when (layout) {
            CalendarDefaults.IndicatorLayout.Column -> {
                IndicatorColumn(itemsToShow, indicator, events)
            }
            CalendarDefaults.IndicatorLayout.Row -> {
                IndicatorRow(itemsToShow, indicator, events)
            }

            CalendarDefaults.IndicatorLayout.Grid -> {
                IndicatorGrid(itemsToShow, indicator, events)
            }
        }

    }
}

@Composable
internal fun <T> IndicatorColumn(
    itemsToShow: List<CalendarEvent<T>>,
    indicator: @Composable (event: CalendarEvent<T>, indicatorPosition: Int, eventsSize: Int) -> Unit,
    events: List<CalendarEvent<T>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.EventIndicatorContainerSize),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            Dimens.EventsSpacedBy,
            Alignment.CenterVertically
        )
    ) {
        itemsToShow.forEachIndexed { index, event ->
            indicator(event, index, events.size)
        }
    }
}

@Composable
internal fun <T> IndicatorRow(
    itemsToShow: List<CalendarEvent<T>>,
    indicator: @Composable (event: CalendarEvent<T>, indicatorPosition: Int, eventsSize: Int) -> Unit,
    events: List<CalendarEvent<T>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.EventIndicatorContainerSize),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.EventsSpacedBy,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsToShow.forEachIndexed { index, event ->
            indicator(event, index, events.size)
        }
    }
}

@Composable
internal fun <T> IndicatorGrid(
    itemsToShow: List<CalendarEvent<T>>,
    indicator: @Composable (event: CalendarEvent<T>, indicatorPosition: Int, eventsSize: Int) -> Unit,
    events: List<CalendarEvent<T>>
) {
    LazyVerticalGrid(
        modifier = Modifier.size(Dimens.EventIndicatorContainerSize),
        columns = GridCells.Fixed(2),
        userScrollEnabled = false,
        contentPadding = PaddingValues(Dimens.None),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.EventsSpacedBy,
            Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(
            Dimens.EventsSpacedBy,
            Alignment.CenterVertically
        )
    ) {
        when (itemsToShow.size) {
             1 -> {
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        indicator(itemsToShow[0], 0, events.size)
                    }
                }
            }

            2 -> {
                items(itemsToShow.size) { index ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        indicator(itemsToShow[index], 1, events.size)
                    }
                }
            }

            3 -> {
                items(2) { index ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        indicator(itemsToShow[index], index, events.size)
                    }
                }
                item(span = { GridItemSpan(2) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        indicator(itemsToShow[2], 2, events.size)
                    }
                }
            }

            4 -> {
                items(itemsToShow.size) { index ->
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        indicator(itemsToShow[index], index, events.size)
                    }
                }
            }
        }
    }
}