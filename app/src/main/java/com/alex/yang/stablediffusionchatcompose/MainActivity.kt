package com.alex.yang.stablediffusionchatcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.ChatScreen
import com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.ChatViewModel
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlexStableDiffusionChatComposeTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<ChatViewModel>()
                    val state by viewModel.uiState.collectAsStateWithLifecycle()

                    ChatScreen(
                        state = state,
                        onSendClick = viewModel::sendMessage
                    )
                }
            }
        }
    }
}