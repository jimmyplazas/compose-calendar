package dev.alejo.composecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.ComposeCalendar
import dev.alejo.compose_calendar.util.CalendarDefaults.calendarColors
import dev.alejo.composecalendar.ui.theme.ComposeCalendarTheme

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
            modifier = Modifier.padding(16.dp),
            events = getListOfEvents(),
            calendarColors = calendarColors(
                eventBackgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                eventContentColor = MaterialTheme.colorScheme.onSurface,
                backgroundColor = Color.White,
                headerBackgroundColor = Color.White
            ),
            onDayClick = { date, event ->
                println(date.toString())
                println(event.toString())
            },
            isContentClickable = false,
            onPreviousMonthClick = { println("Prev") },
            onNextMonthClick = { println("Next") }
        )
    }
}

private fun getListOfEvents() = listOf(
    CalendarEvent(
        data = MyData("Event 1"),
        date = java.time.LocalDate.now().plusDays(1),
        indicatorContent = { IndicatorContent() }
    ),
    CalendarEvent(
        data = MyData("Event 2"),
        date = java.time.LocalDate.now().plusDays(1),
        indicatorContent = { IndicatorContent(Color.Red) }
    ),
    CalendarEvent(
        data = MyData("Event 3"),
        date = java.time.LocalDate.now().plusDays(1),
        indicatorContent = { IndicatorContent() }
    ),
    CalendarEvent(
        data = MyData("Event 4"),
        date = java.time.LocalDate.now().plusDays(1),
        indicatorContent = { IndicatorContent() }
    ),
    CalendarEvent(
        data = MyData("Event 4"),
        date = java.time.LocalDate.now().plusDays(5),
        indicatorContent = { IndicatorContent() }
    ),
)

data class MyData(val name: String)

@Composable
fun IndicatorContent(color: Color = Color.Black) {
    Box(Modifier.size(8.dp).clip(CircleShape).background(color))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeCalendarPreview() {
    Calendar()
}