package com.niyas.moood.di

import android.content.Context
import com.niyas.moood.data.GetSongsHelper
import com.niyas.moood.data.repository.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSongsHelper(@ApplicationContext context: Context) = GetSongsHelper(context)

    @Provides
    @Singleton
    fun provideSongsRepository(getSongsHelper: GetSongsHelper) = SongsRepository(getSongsHelper)
}