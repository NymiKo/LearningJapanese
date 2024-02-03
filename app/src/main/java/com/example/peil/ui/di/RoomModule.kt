package com.example.peil.ui.di

import android.content.Context
import androidx.room.Room
import com.example.peil.data.room.RoomContract
import com.example.peil.data.room.RoomDatabaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): RoomDatabaseApp =
        Room.databaseBuilder(context, RoomDatabaseApp::class.java, RoomContract.databaseApp).build()

    @Provides
    fun provideLessonDao(database: RoomDatabaseApp) = database.lessonDao()

    @Provides
    fun provideSubLessonDao(database: RoomDatabaseApp) = database.subLessonDao()
}