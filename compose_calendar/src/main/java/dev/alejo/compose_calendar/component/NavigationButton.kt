package dev.alejo.compose_calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.alejo.compose_calendar.R
import dev.alejo.compose_calendar.ui.AppDimens
import dev.alejo.compose_calendar.ui.Blue10

@Composable
fun NavigationButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    IconButton(
        modifier = Modifier
            .background(Blue10, RoundedCornerShape(AppDimens.Small)),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.navigation_button_content_description),
            modifier = Modifier.offset(x = 4.dp)
        )
    }
}