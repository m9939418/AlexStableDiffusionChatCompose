package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@Composable
fun BottomAskSection(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    sending: Boolean,
    onSendClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
    ) {
        NikiAskBar(
            enabled = enabled,
            sending = sending,
            onSend = { text ->
                if (enabled && !sending && text.isNotBlank()) {
                    onSendClick(text)
                }
            },
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun BottomAskSectionPreview() {
    AlexStableDiffusionChatComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            BottomAskSection(
                modifier = Modifier.align(Alignment.BottomCenter),
                enabled = true,
                sending = false,
                onSendClick = {},
            )
        }
    }
}