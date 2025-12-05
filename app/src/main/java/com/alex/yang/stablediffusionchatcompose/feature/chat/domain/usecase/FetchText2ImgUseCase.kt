package com.alex.yang.stablediffusionchatcompose.feature.chat.domain.usecase

import com.alex.yang.stablediffusionchatcompose.core.common.Resource
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.model.Text2Img
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
class FetchText2ImgUseCase @Inject constructor(
    val repository: ChatRepository
) {
    suspend operator fun invoke(prompt: String): Resource<Text2Img> {
        return repository.textToImg(prompt)
    }
}