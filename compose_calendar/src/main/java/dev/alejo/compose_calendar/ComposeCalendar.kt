package dev.alejo.compose_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.component.CalendarBody
import dev.alejo.compose_calendar.component.CalendarHeader
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.buildCalendarCache
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Main composable function to render a fully featured calendar with event indicators.
 *
 * Displays a monthly calendar view starting from [initDate], showing days, events, and
 * navigation controls for switching between months. Supports customization of event indicators,
 * click interactions on days, and styling via [calendarColors].
 *
 * @param T The generic type of events associated with calendar dates.
 * @param modifier Modifier to be applied to the calendar container.
 * @param initDate The initial date from which the calendar will display the month. Defaults to the first day of the current month.
 * @param events List of [CalendarEvent] objects representing events to be shown on the calendar.
 * @param onDayClick Lambda invoked when a calendar day is clicked, providing the date and associated events.
 * @param calendarColors Object specifying colors used throughout the calendar UI.
 * @param firstDayOfWeek Specifies the day to be treated as the first day of the week (e.g., Monday, Sunday).
 * @param eventIndicator Optional composable lambda to customize the rendering of individual event indicators.
 * @param maxIndicators The maximum number of event indicators to display per day cell.
 * @param indicatorLayout The layout style used to arrange event indicators within each day cell.
 * @param isContentClickable Flag that enables or disables clicking on individual day cells.
 * @param onPreviousMonthClick Lambda invoked when the user navigates to the previous month.
 * @param onNextMonthClick Lambda invoked when the user navigates to the next month.
 */

@Composable
fun <T> ComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<CalendarEvent<T>> = emptyList(),
    onDayClick: (date: LocalDate, events: List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    eventIndicator: (@Composable (event: CalendarEvent<T>, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit = CalendarDefaults.IndicatorLimit.Four,
    indicatorLayout: CalendarDefaults.IndicatorLayout = CalendarDefaults.IndicatorLayout.Row,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
) {
    val calendarCache = remember(firstDayOfWeek) {
        mutableStateOf(buildCalendarCache(firstDayOfWeek))
    }
    var currentMonth by rememberSaveable { mutableStateOf(initDate) }
    val currentYearMonth = YearMonth.from(currentMonth)

    val firstYearMonth = remember { calendarCache.value.keys.first() }
    val lastYearMonth = remember { calendarCache.value.keys.last() }


    val isPreviousButtonEnable by remember(currentYearMonth) {
        derivedStateOf { currentYearMonth != firstYearMonth }
    }
    val isNextButtonEnable by remember(currentYearMonth) {
        derivedStateOf { currentYearMonth != lastYearMonth }
    }

    val days = calendarCache.value[currentYearMonth] ?: emptyList()

    val groupedEvents by remember(events) {
        derivedStateOf {
            events.groupBy { YearMonth.from(it.date) }
                .mapValues { it.value.groupBy { event -> event.date } }
        }
    }

    val currentMonthEvents by remember(groupedEvents, currentYearMonth) {
        derivedStateOf { groupedEvents[currentYearMonth] ?: emptyMap() }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = calendarColors.backgroundColor,
                shape = RoundedCornerShape(CalendarDefaults.Dimens.Default)
            )
            .padding(CalendarDefaults.Dimens.Default),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.Default)
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            calendarColors = calendarColors,
            firstDayOfWeek = firstDayOfWeek,
            isPreviousButtonEnable = isPreviousButtonEnable,
            isNextButtonEnable = isNextButtonEnable,
            onPreviousMonthClick = {
                if (isPreviousButtonEnable) {
                    onPreviousMonthClick()
                    currentMonth = currentMonth.minusMonths(1)
                }
            },
            onNextMonthClick = {
                if (isNextButtonEnable) {
                    onNextMonthClick()
                    currentMonth = currentMonth.plusMonths(1)
                }
            }
        )
        CalendarBody(
            events = currentMonthEvents,
            days = days,
            onDayClick = onDayClick,
            eventIndicator = eventIndicator,
            maxIndicators = maxIndicators,
            indicatorLayout = indicatorLayout,
            calendarColors = calendarColors,
            isContentClickable = isContentClickable
        )
    }
}

/**
 * Simplified composable calendar function for displaying dates with minimal event data.
 *
 * This wrapper around [ComposeCalendar] accepts a list of [LocalDate] events without additional
 * event details. It converts these dates into generic calendar events internally, allowing easy
 * usage when only event dates matter.
 *
 * @param modifier Modifier to be applied to the calendar container.
 * @param initDate The initial date from which the calendar will display the month. Defaults to the first day of the current month.
 * @param events List of dates representing event days to be shown on the calendar.
 * @param onDayClick Lambda invoked when a calendar day is clicked, providing the date and a list of event dates on that day.
 * @param calendarColors Object specifying colors used throughout the calendar UI.
 * @param firstDayOfWeek Specifies the day to be treated as the first day of the week (e.g., Monday, Sunday).
 * @param eventIndicator Optional composable lambda to customize the rendering of individual event indicators by date.
 * @param maxIndicators The maximum number of event indicators to display per day cell.
 * @param indicatorLayout The layout style used to arrange event indicators within each day cell.
 * @param isContentClickable Flag that enables or disables clicking on individual day cells.
 * @param onPreviousMonthClick Lambda invoked when the user navigates to the previous month.
 * @param onNextMonthClick Lambda invoked when the user navigates to the next month.
 */

@Composable
fun SimpleComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<LocalDate> = emptyList(),
    onDayClick: (date: LocalDate, events: List<LocalDate>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    eventIndicator: (@Composable (event: LocalDate, position: Int, count: Int) -> Unit)?,
    maxIndicators: CalendarDefaults.IndicatorLimit = CalendarDefaults.IndicatorLimit.Four,
    indicatorLayout: CalendarDefaults.IndicatorLayout = CalendarDefaults.IndicatorLayout.Row,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
) {
    ComposeCalendar<Unit>(
        modifier = modifier,
        initDate = initDate,
        events = events.map { CalendarEvent(date = it) },
        onDayClick = { date, dayEvents -> onDayClick(date, dayEvents.map { it.date }) },
        calendarColors = calendarColors,
        firstDayOfWeek = firstDayOfWeek,
        eventIndicator = eventIndicator?.let {
            ({ event, indicatorPosition, eventsSize -> eventIndicator(event.date, indicatorPosition, eventsSize) })
        },
        indicatorLayout = indicatorLayout,
        maxIndicators = maxIndicators,
        isContentClickable = isContentClickable,
        onPreviousMonthClick = onPreviousMonthClick,
        onNextMonthClick = onNextMonthClick
    )
}