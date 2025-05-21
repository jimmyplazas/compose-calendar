package dev.alejo.compose_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.component.CalendarBody
import dev.alejo.compose_calendar.component.CalendarHeader
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * A custom composable calendar that displays a month view with optional event indicators
 * and navigation controls.
 *
 * @param modifier The [Modifier] to be applied to the calendar layout.
 * @param initDate The initial date to display on the calendar. Defaults to the current date.
 * @param events A list of [CalendarEvent] representing events to display on specific dates.
 * @param onDayClick Lambda triggered when a day is clicked.
 *        Passes the clicked [LocalDate] and a list of [CalendarEvent]s associated with that date.
 *        The list will be empty if there are no events for that day.
 * @param calendarColors The color scheme used to style the calendar. Defaults to [CalendarDefaults.calendarColors].
 * @param firstDayOfWeek The first day of the week to display in the calendar. Defaults to the system's default.
 * @param isContentClickable Whether the content of a day cell (e.g., event indicators) is clickable.
 * @param onPreviousMonthClick Callback invoked when the user navigates to the previous month.
 * @param onNextMonthClick Callback invoked when the user navigates to the next month.
 */
@Composable
fun <T> ComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<CalendarEvent<T>> = emptyList(),
    onDayClick: (date: LocalDate, events: List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
) {
    var currentDate by remember { mutableStateOf(initDate) }

    val groupedEvents by remember(events) {
        mutableStateOf(
            events.groupBy { YearMonth.from(it.date) }
                .mapValues { it.value.groupBy { event -> event.date } }
        )
    }

    val currentYearMonth = YearMonth.from(currentDate)
    val currentMonthEvents = groupedEvents[currentYearMonth] ?: emptyMap()

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
            currentDate = currentDate,
            calendarColors = calendarColors,
            firstDayOfWeek = firstDayOfWeek,
            onPreviousMonthClick = {
                onPreviousMonthClick()
                currentDate = currentDate.minusMonths(1)
            },
            onNextMonthClick = {
                onNextMonthClick()
                currentDate = currentDate.plusMonths(1)
            }
        )
        CalendarBody(
            currentDate = currentDate,
            events = currentMonthEvents,
            onDayClick = { date, events -> onDayClick(date, events) },
            calendarColors = calendarColors,
            isContentClickable = isContentClickable,
            firstDayOfWeek = firstDayOfWeek
        )
    }
}

/**
 * A simplified version of [ComposeCalendar] that doesn't require specifying a generic type
 * for event content. It uses [Unit] as the event content type internally.
 *
 * This composable is suitable when you only need basic event indicators (e.g., dots)
 * and don't require custom event data.
 *
 * @param modifier The [Modifier] to be applied to the calendar layout.
 * @param initDate The initial date to display on the calendar. Defaults to the current date.
 * @param events A list of [SimpleCalendarEvent] representing events to display on specific dates.
 * @param onDayClick Lambda triggered when a day is clicked.
 *        Passes the clicked [LocalDate] and a list of [SimpleCalendarEvent]s associated with that date.
 *        The list will be empty if there are no events for that day.
 * @param calendarColors The color scheme used to style the calendar. Defaults to [CalendarDefaults.calendarColors].
 * @param firstDayOfWeek The first day of the week to display in the calendar. Defaults to the system's default.
 * @param isContentClickable Whether the content of a day cell (e.g., event indicators) is clickable.
 * @param onPreviousMonthClick Callback invoked when the user navigates to the previous month.
 * @param onNextMonthClick Callback invoked when the user navigates to the next month.
 */
@Composable
fun SimpleComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = LocalDate.now().withDayOfMonth(1),
    events: List<SimpleCalendarEvent> = emptyList(),
    onDayClick: (date: LocalDate, events: List<SimpleCalendarEvent>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    firstDayOfWeek: DayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek,
    isContentClickable: Boolean = true,
    onPreviousMonthClick: () -> Unit = {},
    onNextMonthClick: () -> Unit = {}
) {
    ComposeCalendar<Unit>(
        modifier = modifier,
        initDate = initDate,
        events = events.map {
            CalendarEvent(
                date = it.date,
                indicatorContent = it.indicator
            )
        },
        onDayClick = { date, dayEvents ->
            onDayClick(date, dayEvents.map { SimpleCalendarEvent(it.date, it.indicatorContent) })
        },
        calendarColors = calendarColors,
        firstDayOfWeek = firstDayOfWeek,
        isContentClickable = isContentClickable,
        onPreviousMonthClick = onPreviousMonthClick,
        onNextMonthClick = onNextMonthClick
    )
}