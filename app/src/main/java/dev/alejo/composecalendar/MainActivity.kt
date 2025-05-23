package dev.alejo.composecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import dev.alejo.compose_calendar.SimpleComposeCalendar
import dev.alejo.compose_calendar.util.CalendarDefaults
import dev.alejo.composecalendar.ui.theme.ComposeCalendarTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MainViewModel()
        setContent {
            val state by viewModel.state.collectAsState()
            ComposeCalendarTheme {
                Scaffold { innerPadding ->
                    Calendar(Modifier.padding(innerPadding), state)
                }
            }
        }
    }
}

@Composable
fun Calendar(modifier: Modifier = Modifier, state: CalendarState) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleComposeCalendar(
            modifier = Modifier.padding(16.dp),
            events = state.simpleEvents,
            onDayClick = { date, event ->
                println(date.toString())
                println(event.toString())
            },
            eventIndicator = { _, _, _ ->
                IndicatorContent(Modifier.size(8.dp))
            }
        )
        ComposeCalendar(
            events = state.events,
            onDayClick = { _, _ -> },
            eventIndicator = { _, position, events ->
                if (position < 2) {
                    IndicatorContent(Modifier.fillMaxWidth().height(8.dp))
                }
                if (position == 2) {
                    Text(
                        text = "+${events - 2}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            },
            maxIndicators = CalendarDefaults.IndicatorLimit.Three,
            indicatorLayout = CalendarDefaults.IndicatorLayout.Column,
            isContentClickable = false,
            onPreviousMonthClick = { println("Prev") },
            onNextMonthClick = { println("Next") }
        )
    }
}

private fun getListOfEvents() = listOf(
    CalendarEvent(
        data = MyData("Event 1"),
        date = LocalDate.now()
    ),
    CalendarEvent(
        data = MyData("Event 1"),
        date = LocalDate.now().minusDays(20)
    ),
    CalendarEvent(
        data = MyData("Event 2"),
        date = LocalDate.now().minusDays(20)
    ),
    CalendarEvent(
        data = MyData("Event 1"),
        date = LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 2"),
        date = LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 3"),
        date = LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 4"),
        date = LocalDate.now().plusDays(1)
    ),
    CalendarEvent(
        data = MyData("Event 3"),
        date = LocalDate.now().plusDays(4)
    ),
    CalendarEvent(
        data = MyData("Event 4"),
        date = LocalDate.now().plusDays(4)
    ),
    CalendarEvent(
        data = MyData("Event 4"),
        date = LocalDate.now().plusDays(4)
    ),
)

data class MyData(val name: String)

@Composable
fun IndicatorContent(modifier: Modifier = Modifier, color: Color = Color.Black) {
    Box(modifier.clip(CircleShape).background(color))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeCalendarPreview() {
    Calendar(
        state = CalendarState(
            events = getListOfEvents(),
            simpleEvents = getListOfEvents().map { it.date }
        )
    )
}
