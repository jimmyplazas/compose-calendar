package dev.alejo.compose_calendar.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

@Composable
fun CalendarDay(
    day: Int?,
    currentDate: LocalDate,
    events: List<CalendarEvent>,
    onDayClick: (date: LocalDate, events: List<CalendarEvent>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors
) {
    val date = if (day != null) {
        LocalDate(
            year = currentDate.year,
            monthNumber = currentDate.monthNumber,
            dayOfMonth = day
        )
    } else {
        null
    }
    val eventsForThisDay = date.let {
        events.filter { it.date == date }
    }
    day?.let {
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
                .clip(RoundedCornerShape(CalendarDefaults.Dimens.Small))
                .background(
                    color = if (eventsForThisDay.isNotEmpty()) {
                        calendarColors.eventBackgroundColor
                    } else {
                        calendarColors.backgroundColor
                    },
                    shape = RoundedCornerShape(CalendarDefaults.Dimens.Small)
                )
                .clickable {
                    onDayClick(
                        LocalDate(
                            year = currentDate.year,
                            monthNumber = currentDate.month.number,
                            dayOfMonth = day
                        ),
                        eventsForThisDay
                    )
                }
                .padding(CalendarDefaults.Dimens.XSmall),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                date?.let {
                    Text(
                        text = date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        color = if(eventsForThisDay.isNotEmpty()) {
                            calendarColors.eventContentColor
                        } else {
                            calendarColors.contentColor
                        }
                    )
                    if (eventsForThisDay.isNotEmpty()) {
                        EventIcon(eventsForThisDay, calendarColors)
                    }
                }
            }
        }
    }
}

@Composable
fun EventIcon(events: List<CalendarEvent>, calendarColors: CalendarColors) {
    if (events.size == 1) {
        events[0].icon?.let {
            Image(
                imageVector = it,
                modifier = Modifier.size(12.dp),
                contentDescription = events[0].description,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(calendarColors.eventContentColor)
            )
        }
    } else {
        LazyVerticalGrid(
            modifier = Modifier.size(24.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center
        ) {
            items(events.size) { index ->
                val event = events[index]
                event.icon?.let {
                    Image(
                        imageVector = it,
                        modifier = Modifier.size(12.dp),
                        contentDescription = event.description,
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(calendarColors.eventContentColor)
                    )
                }
            }
        }
    }
}