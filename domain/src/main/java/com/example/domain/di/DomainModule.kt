package com.example.domain.di

import com.example.domain.repository.ExercisesRepository
import com.example.domain.use_cases.AllExercisesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideAllExercisesUseCase(allExercisesRepository: ExercisesRepository): AllExercisesUseCase {
        return AllExercisesUseCase(allExercisesRepository)
    }

}