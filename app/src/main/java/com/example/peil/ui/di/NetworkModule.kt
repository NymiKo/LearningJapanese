package com.example.peil.ui.di

import android.content.Context
import com.example.peil.data.AuthInterceptor
import com.example.peil.ui.screens.change_nickname.data.ChangeNicknameService
import com.example.peil.ui.screens.create_account.data.CreateAccountService
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonService
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionService
import com.example.peil.ui.screens.lessons_list.data.LessonsListService
import com.example.peil.ui.screens.login.data.LoginService
import com.example.peil.ui.screens.profile.data.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideAuthInterceptor(@ApplicationContext context: Context): AuthInterceptor = AuthInterceptor(context)

    @Provides
    @Singleton
    fun provideClient(logginInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://f0862137.xsph.ru/peil/")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideCreateAccountService(retrofit: Retrofit): CreateAccountService = retrofit.create(CreateAccountService::class.java)

    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService = retrofit.create(LoginService::class.java)

    @Provides
    fun provideLessonsListService(retrofit: Retrofit): LessonsListService = retrofit.create(LessonsListService::class.java)

    @Provides
    fun provideLearningLessonService(retrofit: Retrofit): LearningLessonService = retrofit.create(LearningLessonService::class.java)

    @Provides
    fun provideLessonCompletionService(retrofit: Retrofit): LessonCompletionService = retrofit.create(LessonCompletionService::class.java)

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService = retrofit.create(ProfileService::class.java)

    @Provides
    fun provideChangeNicknameService(retrofit: Retrofit): ChangeNicknameService = retrofit.create(ChangeNicknameService::class.java)
}