package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.api.ExercisesService
import com.example.data.network.utils.SafeApiRequest
import com.example.domain.models.Exercise
import com.example.domain.repository.ExercisesRepository
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(private val exercisesService: ExercisesService): ExercisesRepository, SafeApiRequest() {

    override suspend fun getAllExercises(): List<Exercise> {
        val response = safeApiRequest {exercisesService.getAllExercises()}
        return response.toDomain()
    }

}