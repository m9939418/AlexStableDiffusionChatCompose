package com.alex.yang.stablediffusionchatcompose.feature.chat.data.mapper

import com.alex.yang.stablediffusionchatcompose.feature.chat.data.model.Text2ImgDto
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.model.Text2Img
import java.util.UUID

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
fun Text2ImgDto.toDomain(): Text2Img {
    val url = output
        ?.firstOrNull()
        ?: throw IllegalStateException("No image url in response")

    return Text2Img(
        id = id ?: UUID.randomUUID().leastSignificantBits,
        imageUrl = url
    )
}