package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults.Dimens
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

/**
 * Composable function to display the header of the calendar.
 *
 * @param currentDate The current date displayed in the calendar.
 * @param calendarColors The colors used for styling the calendar.
 * @param firstDayOfWeek The first day of the week to display.
 * @param onPreviousMonthClick Callback function invoked when the previous month button is clicked.
 * @param onNextMonthClick Callback function invoked when the next month button is clicked.
 *
 */
@Composable
fun CalendarHeader(
    currentDate: LocalDate,
    calendarColors: CalendarColors,
    firstDayOfWeek: DayOfWeek,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    val daysOfWeek = (0..6).map { index ->
        DayOfWeek.of(((firstDayOfWeek.value - 1 + index) % 7) + 1)
    }
    val monthName = currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = currentDate.year

    MonthAndNavigation(
        monthName = monthName,
        year = year,
        calendarColors = calendarColors,
        onPreviousMonthClick = onPreviousMonthClick,
        onNextMonthClick = onNextMonthClick
    )
    DayOfWeekHeader(daysOfWeek, calendarColors)
}

/**
 * Composable function to display the month and year, along with navigation buttons.
 *
 * @param monthName The name of the month to display.
 * @param year The year to display.
 * @param calendarColors The colors used for styling the calendar.
 * @param onPreviousMonthClick Callback function invoked when the previous month button is clicked.
 * @param onNextMonthClick Callback function invoked when the next month button is clicked.
 *
 */
@Composable
fun MonthAndNavigation(
    monthName: String,
    year: Int,
    calendarColors: CalendarColors,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = calendarColors.headerBackgroundColor,
                shape = RoundedCornerShape(Dimens.Default)
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.Default)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.Small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimens.Small)
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = "$monthName $year".replaceFirstChar { it.uppercaseChar() },
                color = calendarColors.headerContentColor,
                fontWeight = FontWeight.Bold
            )
            NavigationButton(
                onClick = {
                    onPreviousMonthClick()
                },
                icon = Icons.Default.ArrowBackIosNew,
                backgroundColor = calendarColors.navigationBackgroundColor,
                tintColor = calendarColors.navigationContentColor
            )
            NavigationButton(
                onClick = {
                    onNextMonthClick()
                },
                icon = Icons.Default.ArrowForwardIos,
                backgroundColor = calendarColors.navigationBackgroundColor,
                tintColor = calendarColors.navigationContentColor
            )
        }
    }
}

/**
 * Composable function to display the header for the days of the week.
 *
 * @param daysOfWeek The list of days of the week to display.
 * @param calendarColors The colors used for styling the calendar.
 */
@Composable
fun DayOfWeekHeader(daysOfWeek: List<DayOfWeek>, calendarColors: CalendarColors) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.XSmall)
    ) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = calendarColors.headerContentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}