package dev.alejo.compose_calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alejo.compose_calendar.component.CalendarBody
import dev.alejo.compose_calendar.component.CalendarHeader
import dev.alejo.compose_calendar.ui.AppDimens
import dev.alejo.compose_calendar.ui.DarkerWhite
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import java.time.LocalDate

@Composable
fun ComposeCalendar(
    initDate: LocalDate = LocalDate.now(),
    events: List<CalendarEvent> = emptyList(),
    onDayClick: (CalendarEvent?) -> Unit = {},
    calendarColors: CalendarColors = CalendarDefaults.CalendarColors(
        backgroundColor = DarkerWhite,
        contentColor = DarkerWhite,
        headerBackgroundColor = DarkerWhite,
        headerContentColor = DarkerWhite
    )
) {
    val currentDate = rememberSaveable { mutableStateOf(initDate) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarColors.backgroundColor, RoundedCornerShape(AppDimens.Default))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
    ) {
        CalendarHeader(
            currentYear = currentDate.value.year,
            currentMonth = currentDate.value.monthValue,
            calendarColors = calendarColors,
            onPreviousMonthClick = {
                currentDate.value = currentDate.value.minusMonths(1)
            },
            onNextMonthClick = {
                currentDate.value = currentDate.value.plusMonths(1)
            }
        )
        CalendarBody(
            date = currentDate.value,
            events = events,
            onDayClick = { event -> onDayClick(event) })
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeCalendarPreview() {
    ComposeCalendar(
        events = listOf(
            CalendarEvent(
                title = "Event 1",
                date = LocalDate.now(),
                description = "Description 1",
                icon = Icons.Default.Adb
            )
        )
    )
}