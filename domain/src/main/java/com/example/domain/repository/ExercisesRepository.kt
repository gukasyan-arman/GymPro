package com.example.domain.repository

import com.example.domain.models.Exercise

interface ExercisesRepository {

    suspend fun getAllExercises() : List<Exercise>

}