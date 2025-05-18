package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.compose_calendar.util.toInitDate
import kotlinx.datetime.number
import java.util.Locale

@Composable
fun CalendarHeader(
    currentDate: kotlinx.datetime.LocalDate,
    calendarColors: CalendarColors,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    val locale = remember { Locale.getDefault() }
    val isEnglish = locale.language == "en"

    val dayNames = if (isEnglish) {
        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    } else {
        listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = calendarColors.headerBackgroundColor,
                shape = RoundedCornerShape(CalendarDefaults.Dimens.Default)
            ),
        verticalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.Default)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CalendarDefaults.Dimens.Small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CalendarDefaults.Dimens.Small)
        ) {
            val monthName = CalendarDefaults.getLocalizedMonthName(
                currentDate,
                isEnglish
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "$monthName ${currentDate.year}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = calendarColors.headerContentColor
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