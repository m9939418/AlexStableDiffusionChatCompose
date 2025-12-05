package com.alex.yang.stablediffusionchatcompose.feature.chat.data.api

import com.alex.yang.stablediffusionchatcompose.feature.chat.data.model.Text2ImgDto
import com.alex.yang.stablediffusionchatcompose.feature.chat.data.model.Text2ImgRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
interface StableDiffusionApi {
    @POST("api/v6/images/text2img")
    suspend fun textToImg(@Body request: Text2ImgRequest): Text2ImgDto
}