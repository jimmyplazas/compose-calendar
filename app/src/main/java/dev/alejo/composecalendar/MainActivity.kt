package dev.alejo.composecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.ComposeCalendar
import dev.alejo.compose_calendar.util.CalendarDefaults.calendarColors
import dev.alejo.composecalendar.ui.theme.ComposeCalendarTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalendarTheme {
                Calendar()
            }
        }
    }
}

@Composable
fun Calendar() {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Compose Calendar",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        ComposeCalendar(
            animatedBody = false,
            modifier = Modifier.padding(16.dp),
            events = listOf(
                CalendarEvent(
                    title = "Event 1",
                    date = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
                    description = "Description 1",
                    icon = Icons.Default.Star
                ),
                CalendarEvent(
                    date = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date.plus(
                            DatePeriod(
                                years = 0,
                                months = 0,
                                days = 4
                            )
                        )
                ),
                CalendarEvent(
                    date = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault()).date.plus(
                            DatePeriod(
                                years = 0,
                                months = 0,
                                days = 8
                            )
                        ),
                )
            ),
            calendarColors = calendarColors(
                eventBackgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                eventContentColor = MaterialTheme.colorScheme.onSurface,
                backgroundColor = Color.White,
                headerBackgroundColor = Color.White
            ),
            onDayClick = { event ->
                println(event.toString())
            },
            onPreviousMonthClick = {  },
            onNextMonthClick = {  }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeCalendarPreview() {
    Calendar()
}