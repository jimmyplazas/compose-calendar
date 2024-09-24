package dev.alejo.composecalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import dev.alejo.compose_calendar.ComposeCalendar
import dev.alejo.composecalendar.ui.theme.ComposeCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalendarTheme {
                Scaffold { innerPadding ->
                    Text(modifier = Modifier.padding(innerPadding), text = "")
                    ComposeCalendar()
                }
            }
        }
    }
}