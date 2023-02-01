package com.example.domain.repository

import com.example.domain.models.Exercise
import retrofit2.Response

interface ExercisesRepository {

    suspend fun getAllExercises() : List<Exercise>

    suspend fun getExercisesByBodyPart(bodyPart: String) : List<Exercise>

    suspend fun getExercisesByTarget(target: String) : List<Exercise>

    suspend fun getExercisesByEquipment(equipment: String) : List<Exercise>



}