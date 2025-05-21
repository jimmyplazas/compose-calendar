package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import dev.alejo.compose_calendar.util.CalendarDefaults

/**
 * Composable function for rendering a navigation button.
 *
 * @param onClick The callback to be invoked when the button is clicked.
 * @param icon The [ImageVector] to be displayed as the button's icon.
 * @param modifier The [Modifier] to be applied to the button.
 * @param backgroundColor The background [Color] of the button.
 * @param tintColor The tint [Color] of the icon.
 */
@Composable
fun NavigationButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    tintColor: Color
) {
    IconButton(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(CalendarDefaults.Dimens.Small)
            ),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier,
            tint = tintColor
        )
    }
}