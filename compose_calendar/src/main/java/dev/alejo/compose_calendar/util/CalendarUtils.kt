package dev.alejo.compose_calendar.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal const val YEARS_TO_LOAD = 10

/**
 * Builds a calendar cache containing precomputed days for a wide range of months.
 *
 * For each month in the defined range (from [currentYear - YEARS_TO_LOAD] to [currentYear + YEARS_TO_LOAD]),
 * it generates a list of days including leading nulls to align the first day of the month according to
 * the provided [firstDayOfWeek]. This structure facilitates consistent grid rendering in a calendar UI.
 *
 * @param firstDayOfWeek The starting day of the week (e.g., Monday or Sunday), used to calculate leading empty cells.
 * @return A map where each [YearMonth] is associated with a list of [LocalDate]s (nullable),
 *         representing the full calendar grid for that month.
 */

internal fun buildCalendarCache(firstDayOfWeek: DayOfWeek): Map<YearMonth, List<LocalDate?>> {
    val map = mutableMapOf<YearMonth, List<LocalDate?>>()
    val currentYear = LocalDate.now().year

    for (year in (currentYear - YEARS_TO_LOAD..currentYear + YEARS_TO_LOAD)) {
        for (month in 1..12) {
            val ym = YearMonth.of(year, month)
            val firstDay = ym.atDay(1)
            val dayOfWeek = firstDay.dayOfWeek
            val leadingEmpty = ((dayOfWeek.value - firstDayOfWeek.value + 7) % 7)

            val days = List(leadingEmpty) { null } + List(ym.lengthOfMonth()) { dayOffset ->
                ym.atDay(dayOffset + 1)
            }

            map[ym] = days
        }
    }
    return map
}

/**
 * Preloads calendar data for a range of months surrounding a given center month.
 *
 * This function generates a map of [YearMonth] to a list of [LocalDate]s (nullable),
 * covering 3 months before and 3 months after the specified [centerMonth], inclusive.
 * Each month's list includes leading nulls to align the first day of the month based on
 * the provided [firstDayOfWeek], ensuring proper placement in a calendar grid layout.
 *
 * @param centerMonth The central month around which the data is preloaded.
 * @param firstDayOfWeek The first day of the week used to calculate the number of leading empty cells.
 * @return A map with [YearMonth] keys and lists of [LocalDate?] values representing each month's calendar data.
 */

internal fun preloadMonthsAround(
    centerMonth: LocalDate,
    firstDayOfWeek: DayOfWeek
): Map<YearMonth, List<LocalDate?>> {
    val range = -3..3 //
    return range.associate { offset ->
        val ym = YearMonth.from(centerMonth.plusMonths(offset.toLong()))
        val firstDay = ym.atDay(1)
        val dayOfWeek = firstDay.dayOfWeek
        val leadingEmpty = ((dayOfWeek.value - firstDayOfWeek.value + 7) % 7)
        val days = List(leadingEmpty) { null } + List(ym.lengthOfMonth()) {
            ym.atDay(it + 1)
        }
        ym to days
    }
}

