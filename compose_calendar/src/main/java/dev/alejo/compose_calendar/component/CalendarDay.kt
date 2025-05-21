package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.alejo.compose_calendar.CalendarEvent
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults.Dimens
import java.time.LocalDate

/**
 * Composable que representa un día individual en el calendario.
 *
 * Muestra el número del día y un indicador visual si existen eventos asociados.
 * Si hay más de dos eventos, muestra un contador con el número adicional de eventos.
 * El fondo y el color del texto cambian dependiendo de si hay eventos o no.
 * Además, puede ser clickeable para manejar la interacción con el día.
 *
 * @param T Tipo genérico asociado a los eventos.
 * @param day Fecha representada por este día del calendario. Puede ser null para celdas vacías.
 * @param events Lista de eventos asociados a esta fecha.
 * @param isContentClickable Indica si el contenido es clickeable. Si es true, el día responderá al click.
 * @param onDayClick Lambda que se ejecuta al hacer click en el día, recibe la fecha y la lista de eventos.
 *                     Por defecto no hace nada.
 * @param calendarColors Objeto con los colores usados para el diseño del día, tanto fondo como texto.
 */

@Composable
fun <T> CalendarDay(
    day: LocalDate?,
    events: List<CalendarEvent<T>>,
    isContentClickable: Boolean,
    onDayClick: (date: LocalDate, events: List<CalendarEvent<T>>) -> Unit = { _, _ -> },
    calendarColors: CalendarColors
) {
    day?.let { date ->
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = Dimens.DaySize, minHeight = Dimens.DaySize)
                .clip(RoundedCornerShape(Dimens.Small))
                .background(
                    color = if (events.isNotEmpty()) {
                        calendarColors.eventBackgroundColor
                    } else {
                        calendarColors.backgroundColor
                    },
                    shape = RoundedCornerShape(Dimens.Small)
                )
                .then(
                    if(isContentClickable) {
                        Modifier.clickable { onDayClick(date, events) }
                    } else {
                        Modifier
                    }
                )
                .padding(Dimens.XSmall),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(Dimens.Small)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    color = if(events.isNotEmpty()) {
                        calendarColors.eventContentColor
                    } else {
                        calendarColors.contentColor
                    }
                )
                if (events.isNotEmpty()) {
                    EventIcon(events)
                    if (events.size > 2) {
                        Text(
                            text = "+${events.size - 2}",
                            style = MaterialTheme.typography.labelSmall,
                            color = calendarColors.eventContentColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable que muestra hasta dos iconos indicadores para los eventos de un día.
 *
 * Se muestran los indicadores de los primeros dos eventos (si existen),
 * colocados en una fila con espacio entre ellos y centrados verticalmente.
 *
 * @param T Tipo genérico asociado a los eventos.
 * @param events Lista de eventos de los cuales se mostrarán los indicadores.
 */

@Composable
fun <T> EventIcon(events: List<CalendarEvent<T>>) {
    Row(
        modifier = Modifier.height(Dimens.IconSize),
        horizontalArrangement = Arrangement.spacedBy(Dimens.EventsSpacedBy),
        verticalAlignment = Alignment.CenterVertically
    ) {
        events.take(2).forEach { it.indicatorContent?.invoke() }
    }
}