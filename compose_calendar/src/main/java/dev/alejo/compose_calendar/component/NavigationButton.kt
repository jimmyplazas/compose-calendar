package dev.alejo.compose_calendar.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.alejo.compose_calendar.util.CalendarColors
import dev.alejo.compose_calendar.util.CalendarDefaults.Dimens

/**
 * Composable function for rendering a navigation button.
 *
 * @param onClick The callback to be invoked when the button is clicked.
 * @param icon The [ImageVector] to be displayed as the button's icon.
 * @param modifier The [Modifier] to be applied to the button.
 */
@Composable
fun NavigationButton(
    onClick: () -> Unit,
    icon: ImageVector,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    calendarColors: CalendarColors
) {
    Button(
        modifier = Modifier
            .size(Dimens.ButtonSize)
            .clip(RoundedCornerShape(Dimens.Small)),
        enabled = enabled,
        shape = RoundedCornerShape(Dimens.Small),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonColors(
            containerColor = calendarColors.navigationContainerColor,
            contentColor = calendarColors.navigationContentColor,
            disabledContainerColor = calendarColors.navigationDisableContainerColor,
            disabledContentColor = calendarColors.navigationDisableContentColor
        ),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.size(Dimens.Default)
        )
    }
}