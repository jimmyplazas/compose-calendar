package dev.alejo.compose_calendar.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.compose_calendar.ui.AppDimens
import java.time.LocalDate

@Composable
fun CalendarBody(
    startYear: Int = LocalDate.now().year,
    startMonth: Int = LocalDate.now().monthValue
) {
    val startDate = LocalDate.of(startYear, startMonth, 1)
    val daysInMonth = startDate.lengthOfMonth()
    val startDayOfWeek = startDate.dayOfWeek.value % 7

    // List of days including leading empty days
    val days = List(startDayOfWeek) { "" } + List(daysInMonth) { it + 1 }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(AppDimens.Small),
        horizontalArrangement = Arrangement.spacedBy(AppDimens.XSmall),
        verticalArrangement = Arrangement.spacedBy(AppDimens.XSmall)
    ) {
        items(days) { day ->
            CalendarDay(day = day, events = emptyList(), year = startYear, month = startMonth)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    CalendarBody()
}