package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
data class ChatMessage(
    val id: Long,
    val sender: Sender,
    val text: String? = null,
    val imageUrl: String? = null,
    val isTyping: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

enum class Sender {
    USER,
    BOT
}