package com.example.domain.di

import com.example.domain.repository.ExercisesRepository
import com.example.domain.use_cases.AllExercisesUseCase
import com.example.domain.use_cases.ExercisesByBodyPartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideAllExercisesUseCase(exercisesRepository: ExercisesRepository): AllExercisesUseCase {
        return AllExercisesUseCase(exercisesRepository)
    }

    @Provides
    fun provideExercisesByBodyPart(exercisesRepository: ExercisesRepository): ExercisesByBodyPartUseCase {
        return ExercisesByBodyPartUseCase(exercisesRepository)
    }

}