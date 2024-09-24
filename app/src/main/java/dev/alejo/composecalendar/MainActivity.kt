package dev.alejo.composecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalendarTheme {

            }
        }
    }
}

@Composable
fun Calendar() {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Compose Calendar", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        ComposeCalendar(
            modifier = Modifier.padding(16.dp),
            events = listOf(
                CalendarEvent(
                    title = "Event 1",
                    date = LocalDate.now(),
                    description = "Description 1",
                    icon = Icons.Default.Star
                ),
                CalendarEvent(
                    title = "Event 1",
                    date = LocalDate.now().plusDays(3),
                    description = "Description 1",
                    icon = Icons.Default.Star
                ),
                CalendarEvent(
                    title = "Event 1",
                    date = LocalDate.now().minusDays(12),
                    description = "Description 1",
                    icon = Icons.Default.Star
                )
            ),
            calendarColors = calendarColors(
                eventBackgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                eventContentColor = MaterialTheme.colorScheme.onSurface,
                backgroundColor = Color.White,
                headerBackgroundColor = Color.White
            ),
            onDayClick = {
                /* Do Something */
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeCalendarPreview() {
    Calendar()
}