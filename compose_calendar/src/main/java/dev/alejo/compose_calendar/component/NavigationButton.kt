package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import dev.alejo.compose_calendar.R
import dev.alejo.compose_calendar.ui.AppDimens

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
            .background(backgroundColor, RoundedCornerShape(AppDimens.Small)),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.navigation_button_content_description),
            modifier = modifier,
            tint = tintColor
        )
    }
}