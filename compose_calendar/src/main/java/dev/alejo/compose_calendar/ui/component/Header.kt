package dev.alejo.compose_calendar.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.alejo.compose_calendar.ui.utils.AppDimens

@Composable
fun Header(
    text: String,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppDimens.Default)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        NavigationButton(
            onClick = {
                onPreviousMonthClick()
            },
            icon = Icons.AutoMirrored.Filled.ArrowBackIos
        )
        NavigationButton(
            onClick = {
                onNextMonthClick()
            },
            icon = Icons.AutoMirrored.Filled.ArrowForwardIos
        )
    }
}