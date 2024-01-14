package com.example.peil.ui.di

import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import com.example.peil.ui.screens.create_account.data.CreateAccountRepositoryImpl
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonRepository
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonRepositoryImpl
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionRepository
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionRepositoryImpl
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepository
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepositoryImpl
import com.example.peil.ui.screens.login.data.LoginRepository
import com.example.peil.ui.screens.login.data.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCreateAccountRepository(repository: CreateAccountRepositoryImpl): CreateAccountRepository

    @Binds
    @Singleton
    fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindLessonsListRepository(repository: LessonsListRepositoryImpl): LessonsListRepository

    @Binds
    @Singleton
    fun bindLearningLessonRepository(repository: LearningLessonRepositoryImpl): LearningLessonRepository

    @Binds
    @Singleton
    fun bindLessonCompletionRepository(repository: LessonCompletionRepositoryImpl): LessonCompletionRepository
}