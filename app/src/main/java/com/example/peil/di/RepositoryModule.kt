package com.example.peil.di

import com.example.peil.main.MainRepository
import com.example.peil.main.MainRepositoryImpl
import com.example.peil.ui.screens.change_nickname.data.ChangeNicknameRepository
import com.example.peil.ui.screens.change_nickname.data.ChangeNicknameRepositoryImpl
import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import com.example.peil.ui.screens.create_account.data.CreateAccountRepositoryImpl
import com.example.peil.ui.screens.entering_mail.data.EnteringEmailRepository
import com.example.peil.ui.screens.entering_mail.data.EnteringEmailRepositoryImpl
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonRepository
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonRepositoryImpl
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionRepository
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionRepositoryImpl
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepository
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepositoryImpl
import com.example.peil.ui.screens.login.data.LoginRepository
import com.example.peil.ui.screens.login.data.LoginRepositoryImpl
import com.example.peil.ui.screens.profile.data.ProfileRepository
import com.example.peil.ui.screens.profile.data.ProfileRepositoryImpl
import com.example.peil.ui.screens.settings.data.SettingsRepository
import com.example.peil.ui.screens.settings.data.SettingsRepositoryImpl
import com.example.peil.ui.screens.verification.data.VerificationRepository
import com.example.peil.ui.screens.verification.data.VerificationRepositoryImpl
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

    @Binds
    @Singleton
    fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    fun bindChangeNicknameRepository(repository: ChangeNicknameRepositoryImpl): ChangeNicknameRepository

    @Binds
    @Singleton
    fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    fun bindMainRepository(repository: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun bindVerificationRepository(repository: VerificationRepositoryImpl): VerificationRepository

    @Binds
    @Singleton
    fun bindEnteringEmailRepository(repository: EnteringEmailRepositoryImpl): EnteringEmailRepository
}