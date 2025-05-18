package dev.alejo.compose_calendar.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil
import kotlinx.datetime.number

fun LocalDate.daysOfTheMonth(): Int {
    val firstDayOfMonth = LocalDate(this.year, this.month, 1)
    val firstDayOfNextMonth = if (this.month.number == 12) {
        LocalDate(this.year + 1, 1, 1)
    } else {
        LocalDate(this.year, this.month.number + 1, 1)
    }
    return firstDayOfMonth.daysUntil(firstDayOfNextMonth)
}

fun LocalDate.toInitDate(): LocalDate = LocalDate(this.year, this.month.number, 1)