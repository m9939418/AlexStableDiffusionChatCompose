package com.alex.yang.stablediffusionchatcompose.feature.chat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
@Serializable
data class Text2ImgRequest(
    @SerialName("key") val key: String,
    @SerialName("model_id") val modelId: String = "flux",
    @SerialName("prompt") val prompt: String,
    @SerialName("width") val width: String = "512",
    @SerialName("height") val height: String = "512",
    @SerialName("samples") val samples: String = "1",
    @SerialName("num_inference_steps") val numInferenceSteps: String = "20",
    @SerialName("safety_checker") val safetyChecker: String = "no",
    @SerialName("seed") val seed: Long? = null,
    @SerialName("guidance_scale") val guidanceScale: Double = 7.5,
    @SerialName("clip_skip") val clipSkip: String = "2",
    @SerialName("vae") val vae: String? = null,
    @SerialName("webhook") val webhook: String? = null,
    @SerialName("track_id") val trackId: String? = null
)
