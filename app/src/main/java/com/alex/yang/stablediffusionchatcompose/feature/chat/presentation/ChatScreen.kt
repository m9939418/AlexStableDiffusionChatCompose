package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component.BottomAskSection
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component.ChatMessageRow
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatViewModel.UiState,
    onSendClick: (String) -> Unit = {},
) {
    val listState = rememberLazyListState()

    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 48.dp),
            state = listState
        ) {
            items(
                state.messages,
                key = { it.id }
            ) { item ->
                ChatMessageRow(message = item)
            }
        }

        BottomAskSection(
            modifier = Modifier.imePadding(),
            enabled = !state.isLoading,
            sending = state.isLoading,
            onSendClick = { textToSend ->
                val text = textToSend.trim()
                if (text.isEmpty()) return@BottomAskSection
                onSendClick(text)
            }
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
fun ChatScreenPreview() {
    AlexStableDiffusionChatComposeTheme {
        ChatScreen(
            state = ChatViewModel.UiState(
                messages = listOf(
                    ChatMessage(
                        id = 1L,
                        sender = Sender.USER,
                        text = "請幫我生成一張貓咪在太空中漂浮的圖片。",
                    ),
                    ChatMessage(
                        id = 2L,
                        sender = Sender.BOT,
                        imageUrl = "https://picsum.photos/300/200"
                    ),
                    ChatMessage(
                        id = 3L,
                        sender = Sender.USER,
                        text = "再來一張狗狗在海邊玩的圖片。",
                    )
                )
            )
        )
    }
}