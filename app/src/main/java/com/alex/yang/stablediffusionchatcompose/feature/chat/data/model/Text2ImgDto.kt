package com.alex.yang.stablediffusionchatcompose.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
@Serializable
data class Text2ImgDto(
    @SerialName("status") val status: String,
    @SerialName("generationTime") val generationTime: Double? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("output") val output: List<String>? = null,
)
