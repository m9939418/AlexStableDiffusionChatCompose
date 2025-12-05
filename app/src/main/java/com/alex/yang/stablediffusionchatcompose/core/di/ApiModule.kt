package com.alex.yang.stablediffusionchatcompose.core.di

import com.alex.yang.stablediffusionchatcompose.feature.chat.data.api.StableDiffusionApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by AlexYang on 2025/12/5.
 *
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApiHost(): String = "https://modelslab.com"

    @Provides
    @Singleton
    fun provideKotlinJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideKotlinxConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideRetrofit(
        apiHost: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(apiHost)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideStableDiffusionApi(retrofit: Retrofit): StableDiffusionApi =
        retrofit.create<StableDiffusionApi>()
}