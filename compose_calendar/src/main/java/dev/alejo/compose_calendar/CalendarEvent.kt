package dev.alejo.compose_calendar

import androidx.compose.runtime.Composable
import java.time.LocalDate

/**
 * Representa un evento en el calendario asociado a una fecha específica.
 *
 * @param T Tipo genérico que representa los datos asociados al evento.
 * @property data Información adicional opcional relacionada con el evento.
 * @property date Fecha del evento.
 * @property indicatorContent Composable opcional que representa un icono o indicador visual para el evento.
 */
data class CalendarEvent<T>(
    val data: T? = null,
    val date: LocalDate,
    val indicatorContent: (@Composable () -> Unit)? = null
)