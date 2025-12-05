package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.di

import com.alex.yang.stablediffusionchatcompose.feature.chat.data.repository.ChatRepositoryImpl
import com.alex.yang.stablediffusionchatcompose.feature.chat.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ChatBinds {
    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: ChatRepositoryImpl): ChatRepository
}