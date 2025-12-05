package com.alex.yang.stablediffusionchatcompose.feature.chat.data.repository

import com.alex.yang.stablediffusionchatcompose.BuildConfig
import com.alex.yang.stablediffusionchatcompose.core.common.Resource
import com.alex.yang.stablediffusionchatcompose.feature.chat.data.api.StableDiffusionApi
import com.alex.yang.stablediffusionchatcompose.feature.chat.data.mapper.toDomain
import com.alex.yang.stablediffusionchatcompose.feature.chat.data.model.Text2ImgRequest
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.model.Text2Img
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.repository.ChatRepository
import javax.inject.Inject

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
class ChatRepositoryImpl @Inject constructor(
    val api: StableDiffusionApi
) : ChatRepository {
    override suspend fun textToImg(prompt: String): Resource<Text2Img> {
        return try {
            val request = Text2ImgRequest(
                key = BuildConfig.STABLE_DIFFUSION_API_KEY,
                prompt = prompt
            )

            val response = api.textToImg(request)


            if (response.status != "success") {
                Resource.Error(
                    message = "API status: ${response.status}",
                    throwable = IllegalStateException("API status: ${response.status}")
                )
            }

            Resource.Success(data = response.toDomain())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error", e)
        }
    }
}