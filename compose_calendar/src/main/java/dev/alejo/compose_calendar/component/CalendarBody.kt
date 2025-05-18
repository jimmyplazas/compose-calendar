package dev.alejo.compose_calendar.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.daysOfTheMonth
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun CalendarBody(
    currentDate: LocalDate,
    events: List<CalendarEvent>,
    onDayClick: (LocalDate, List<CalendarEvent>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors,
    animatedBody: Boolean
) {
    val date = LocalDate(currentDate.year, currentDate.monthNumber, 1)

    /**
     * List of days including leading empty days
     *
     * The first list is the leading empty days. Which is the number of the dayOfWeek
     * minus 1, for example:
     * If the first day of the week is 3, it means it is Wednesday, then it must be 3 - 1 which is
     * Tuesday, so as a result it is: [Monday: null, Tuesday: null, Wednesday: 1, Thursday:2, ...]
     *
     * The second list is the days of the month
     */
    val leadingEmptyDays = date.dayOfWeek.isoDayNumber - 1
    val days = List(leadingEmptyDays) { null } + List(currentDate.daysOfTheMonth()) { it + 1 }

    if (animatedBody) {
        AnimatedContent(days) {
            BodyContent(days, currentDate, events, onDayClick, calendarColors)
        }
    } else {
        BodyContent(days, currentDate, events, onDayClick, calendarColors)
    }
}

@Composable
private fun BodyContent(
    days: List<Int?>,
    currentDate: LocalDate,
    events: List<CalendarEvent>,
    onDayClick: (LocalDate, List<CalendarEvent>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarColors.backgroundColor),
        horizontalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.XSmall)
    ) {
        items(days) { day ->
            CalendarDay(
                day = day,
                events = events,
                currentDate = currentDate,
                onDayClick = { date, events -> onDayClick(date, events) },
                calendarColors = calendarColors
            )
        }
    }
}