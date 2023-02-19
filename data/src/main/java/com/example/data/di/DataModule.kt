package com.example.data.di

import com.example.common.Constant
import com.example.data.network.api.ExercisesService
import com.example.data.repository.ExercisesRepositoryImpl
import com.example.domain.repository.ExercisesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideExerciseRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.EXERCISE_DB_BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideExerciseService(retrofit: Retrofit): ExercisesService {
        return retrofit.create(ExercisesService::class.java)
    }

    @Provides
    fun provideExercisesRepository(exercisesService: ExercisesService): ExercisesRepository {
        return ExercisesRepositoryImpl(exercisesService = exercisesService)
    }



    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

}