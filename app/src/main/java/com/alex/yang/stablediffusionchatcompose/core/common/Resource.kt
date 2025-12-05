package com.alex.yang.stablediffusionchatcompose.core.common

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>

    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : Resource<Nothing>
}