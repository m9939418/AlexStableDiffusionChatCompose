package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.ChatMessage
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.Sender
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@Composable
fun ChatMessageRow(
    modifier: Modifier = Modifier,
    message: ChatMessage
) {
    val isUser = message.sender == Sender.USER

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!isUser) {
            // Bot avatar
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "N",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        } else {
            Spacer(modifier = Modifier.width(44.dp))
        }

        Column(
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomEnd = if (isUser) 0.dp else 16.dp,
                    bottomStart = if (isUser) 16.dp else 0.dp
                ),
                color = if (isUser)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 280.dp)
                        .padding(12.dp)
                ) {
                    when {
                        // Loading...
                        message.isTyping -> {
                            BouncingDotsLoading()
                        }

                        else -> {
                            // 一般文字
                            message.text?.let {
                                Text(
                                    text = it,
                                    color = if (isUser)
                                        MaterialTheme.colorScheme.onPrimary
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // 圖片
                            message.imageUrl?.let { url ->
                                Spacer(modifier = Modifier.height(8.dp))
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1.0f),
                                    model = url,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }

        if (isUser) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Composable
fun ChatMessageRowBotPreview() {
    AlexStableDiffusionChatComposeTheme {
        ChatMessageRow(
            message = ChatMessage(
                id = 1L,
                sender = Sender.BOT,
                text = null,
                imageUrl = "https://picsum.photos/300/200"
            )
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Composable
fun ChatMessageRowUserPreview() {
    AlexStableDiffusionChatComposeTheme {
        ChatMessageRow(
            message = ChatMessage(
                id = 1L,
                sender = Sender.USER,
                text = "這是一則來自機器人的訊息，內容包含了一些文字描述以及一張圖片。",
            )
        )
    }
}