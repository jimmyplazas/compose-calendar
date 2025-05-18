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