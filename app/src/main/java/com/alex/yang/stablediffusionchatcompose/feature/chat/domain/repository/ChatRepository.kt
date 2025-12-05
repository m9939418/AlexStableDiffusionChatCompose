package com.alex.yang.stablediffusionchatcompose.feature.chat.domain.repository

import com.alex.yang.stablediffusionchatcompose.core.common.Resource
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.model.Text2Img

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
interface ChatRepository {
    suspend fun textToImg(prompt: String): Resource<Text2Img>
}