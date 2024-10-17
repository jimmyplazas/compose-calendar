package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.CalendarDefaults.calendarColors
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarHeader(
    currentYear: Int,
    currentMonth: Int,
    calendarColors: CalendarColors,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    val dayNames = if (Locale.getDefault().language == Locale.ENGLISH.language) {
        listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
    } else {
        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarColors.headerBackgroundColor, RoundedCornerShape(CalendarDefaults.Dimens.Default)),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.Default)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = CalendarDefaults.Dimens.Small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.Small)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = LocalDate.of(currentYear, currentMonth, 1)
                    .month
                    .getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentYear,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = calendarColors.headerContentColor
            )
            NavigationButton(
                onClick = {
                    onPreviousMonthClick()
                },
                icon = Icons.AutoMirrored.Filled.ArrowBackIos,
                modifier = Modifier
                    .offset(x = CalendarDefaults.Dimens.XSmall),
                backgroundColor = calendarColors.navigationBackgroundColor,
                tintColor = calendarColors.navigationContentColor
            )
            NavigationButton(
                onClick = {
                    onNextMonthClick()
                },
                icon = Icons.AutoMirrored.Filled.ArrowForwardIos,
                backgroundColor = calendarColors.navigationBackgroundColor,
                tintColor = calendarColors.navigationContentColor
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            dayNames.forEach { dayName ->
                Text(
                    text = dayName,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = calendarColors.headerContentColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    CalendarHeader(
        currentYear = LocalDate.now().year,
        currentMonth = LocalDate.now().monthValue,
        onPreviousMonthClick = {},
        onNextMonthClick = {},
        calendarColors = calendarColors()
    )
}