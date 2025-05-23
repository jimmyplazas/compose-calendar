package dev.alejo.compose_calendar

import java.time.LocalDate

/**
 * Representa un evento en el calendario asociado a una fecha específica.
 *
 * @param T Tipo genérico que representa los datos asociados al evento.
 * @property data Información adicional opcional relacionada con el evento.
 * @property date Fecha del evento.
 */
data class CalendarEvent<T>(
    val data: T? = null,
    val date: LocalDate
)