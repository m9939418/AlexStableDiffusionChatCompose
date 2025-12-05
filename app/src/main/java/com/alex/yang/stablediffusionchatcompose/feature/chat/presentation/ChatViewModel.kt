package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.yang.stablediffusionchatcompose.core.common.Resource
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.usecase.FetchText2ImgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: FetchText2ImgUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(
        UiState(
            messages = listOf(
                ChatMessage(
                    id = 1L,
                    sender = Sender.BOT,
                    text = "嗨，我是 Niki～輸入一句描述，我幫你用 Flux 生一張圖 🐱✨"
                )
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    private var nextId: Long = 2L

    fun sendMessage(prompt: String) {
        val trimmed = prompt.trim()
        val current = _uiState.value

        // 空字串 or 正在送出就直接擋掉
        if (trimmed.isBlank() || current.isLoading) return

        // 使用者訊息
        val userMsg = ChatMessage(
            id = nextId++,
            sender = Sender.USER,
            text = trimmed
        )

        // Loading...
        val typingMsg = ChatMessage(
            id = nextId++,
            sender = Sender.BOT,
            isTyping = true
        )
        val typingId = typingMsg.id

        _uiState.value = current.copy(
            isLoading = true,
            messages = current.messages + userMsg + typingMsg
        )

        viewModelScope.launch {
            when (val resource = useCase(trimmed)) {
                is Resource.Success -> {
                    val botMsg = ChatMessage(
                        id = nextId++,
                        sender = Sender.BOT,
                        text = "",
                        imageUrl = resource.data.imageUrl
                    )

                    val newMessages = _uiState.value.messages
                        .filterNot { it.id == typingId } + botMsg

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        messages = newMessages
                    )
                }

                is Resource.Error -> {
                    val errMsg = ChatMessage(
                        id = nextId++,
                        sender = Sender.BOT,
                        text = "生成失敗：${resource.message}"
                    )

                    val newMessages = _uiState.value.messages
                        .filterNot { it.id == typingId } + errMsg

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        messages = newMessages
                    )
                }
            }
        }
    }

    data class UiState(
        val input: String = "",
        val isLoading: Boolean = false,
        val messages: List<ChatMessage> = emptyList()
    )
}