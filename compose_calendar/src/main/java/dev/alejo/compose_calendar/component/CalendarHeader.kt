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
 * Composable that renders the calendar header section.
 *
 * Displays the current month and year with navigation controls, and shows the days of the week
 * arranged according to the specified first day of the week. Combines the month navigation
 * bar and the day-of-week labels in a cohesive header UI.
 *
 * @param currentMonth The [LocalDate] representing the current month and year to display.
 * @param calendarColors Colors used to style the header components such as background and text.
 * @param firstDayOfWeek The day of the week that should appear as the first column in the calendar (e.g., Monday or Sunday).
 * @param isPreviousButtonEnable Flag to enable or disable the button that navigates to the previous month.
 * @param isNextButtonEnable Flag to enable or disable the button that navigates to the next month.
 * @param onPreviousMonthClick Lambda invoked when the previous month navigation button is clicked.
 * @param onNextMonthClick Lambda invoked when the next month navigation button is clicked.
 */

@Composable
fun CalendarHeader(
    currentMonth: LocalDate,
    calendarColors: CalendarColors,
    firstDayOfWeek: DayOfWeek,
    isPreviousButtonEnable: Boolean,
    isNextButtonEnable: Boolean,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    val daysOfWeek = (0..6).map { index ->
        DayOfWeek.of(((firstDayOfWeek.value - 1 + index) % 7) + 1)
    }
    val monthName = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = currentMonth.year

    MonthAndNavigation(
        monthName = monthName,
        year = year,
        isPreviousButtonEnable = isPreviousButtonEnable,
        isNextButtonEnable = isNextButtonEnable,
        calendarColors = calendarColors,
        onPreviousMonthClick = onPreviousMonthClick,
        onNextMonthClick = onNextMonthClick
    )
    DayOfWeekHeader(daysOfWeek, calendarColors)
}

/**
 * Composable that displays the current month and year with navigation controls.
 *
 * Shows the month name and year in a styled header with buttons to navigate to the
 * previous and next months. The navigation buttons can be enabled or disabled based
 * on availability. The entire header is styled with provided colors and shapes.
 *
 * @param monthName The localized name of the current month to display (e.g., "May").
 * @param year The year number to display alongside the month.
 * @param isPreviousButtonEnable Boolean flag to enable or disable the "previous month" button.
 * @param isNextButtonEnable Boolean flag to enable or disable the "next month" button.
 * @param calendarColors Colors used to style the header background, text, and buttons.
 * @param onPreviousMonthClick Lambda invoked when the previous month button is clicked.
 * @param onNextMonthClick Lambda invoked when the next month button is clicked.
 */

@Composable
fun MonthAndNavigation(
    monthName: String,
    year: Int,
    isPreviousButtonEnable: Boolean,
    isNextButtonEnable: Boolean,
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
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            NavigationButton(
                onClick = onPreviousMonthClick,
                icon = Icons.Default.ArrowBackIosNew,
                enabled = isPreviousButtonEnable,
                calendarColors = calendarColors
            )
            NavigationButton(
                onClick = onNextMonthClick,
                icon = Icons.Default.ArrowForwardIos,
                enabled = isNextButtonEnable,
                calendarColors = calendarColors
            )
        }
    }
}

/**
 * Composable that renders the header row displaying the days of the week.
 *
 * Each day of the week is shown using its short localized name (e.g., Mon, Tue),
 * with the first character capitalized. The days are evenly distributed horizontally
 * across the available width.
 *
 * @param daysOfWeek A list of [DayOfWeek] representing the sequence of days to display.
 * @param calendarColors The set of colors to apply for styling the header text.
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
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    .replaceFirstChar { it.uppercaseChar() },
                style = MaterialTheme.typography.bodyMedium,
                color = calendarColors.headerContentColor,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}