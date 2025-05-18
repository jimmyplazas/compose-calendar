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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.component.CalendarBody
import dev.alejo.compose_calendar.component.CalendarHeader
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.toInitDate
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

/**
 * A custom composable calendar that displays a month view with optional event indicators.
 *
 * @param modifier The [Modifier] to be applied to the calendar layout.
 * @param initDate The initial date to display on the calendar. Defaults to the current date.
 * @param events A list of [CalendarEvent] representing events to display on specific dates.
 * @param onDayClick Lambda that is triggered when a day is clicked.
 *        Passes the corresponding [CalendarEvent] if one exists, or `null` otherwise.
 * @param calendarColors The color scheme for customizing the appearance of the calendar.
 *        Defaults to [CalendarDefaults.calendarColors].
 * @param animatedBody Whether the body of the calendar (days grid) should animate
 *        during month transitions.
 */

@Composable
fun ComposeCalendar(
    modifier: Modifier = Modifier,
    initDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date.toInitDate(),
    events: List<CalendarEvent> = emptyList(),
    onDayClick: (CalendarEvent?) -> Unit = {},
    calendarColors: CalendarColors = CalendarDefaults.calendarColors(),
    animatedBody: Boolean = true
) {
    val localDateSaver: Saver<LocalDate, String> = Saver(
        save = { it.toString() },
        restore = { LocalDate.parse(it) }
    )

    var currentDate by rememberSaveable(stateSaver = localDateSaver) {
        mutableStateOf(initDate)
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
            currentDate = currentDate,
            calendarColors = calendarColors,
            onPreviousMonthClick = {
                currentDate = currentDate.minus(DatePeriod(years = 0, months = 1, days = 0))
            },
            onNextMonthClick = {
                currentDate = currentDate.plus(DatePeriod(years = 0, months = 1, days = 0))
            }
        )
        CalendarBody(
            currentDate = currentDate,
            events = events,
            onDayClick = { event -> onDayClick(event) },
            calendarColors = calendarColors,
            animatedBody = animatedBody
        )
    }
}