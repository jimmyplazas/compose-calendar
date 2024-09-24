package dev.alejo.compose_calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.ui.AppDimens
import dev.alejo.compose_calendar.ui.Blue05
import dev.alejo.compose_calendar.ui.DarkerWhite
import java.time.LocalDate

@Composable
fun CalendarDay(day: Any, currentDate: LocalDate, events: List<CalendarEvent>) {
    val date = if (day is Int) LocalDate.of(currentDate.year, currentDate.monthValue, day) else null
    val eventForThisDay = date?.let {
        events.find { it.date == date }
    }
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                eventForThisDay?.let { Blue05 } ?: DarkerWhite,
                RoundedCornerShape(AppDimens.Small)
            )
            .padding(AppDimens.XSmall),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (date != null) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(3f)
                )

                eventForThisDay?.let { event ->
                    Image(
                        imageVector = event.icon,
                        contentDescription = event.description,
                        modifier = Modifier.weight(2f),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayPreview() {
    val events = listOf(
        CalendarEvent(
            title = "Event 1",
            date = LocalDate.now(),
            description = "Description 1",
            icon = Icons.Default.Adb
        )
    )
    val day = LocalDate.now().dayOfMonth
    val month = LocalDate.now().monthValue
    val year = LocalDate.now().year
    CalendarDay(day = day, currentDate = LocalDate.of(year, month, day), events = events)
}