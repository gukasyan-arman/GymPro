package com.example.data.network.api

import com.example.common.Constant
import com.example.data.network.models.ExerciseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExercisesService {

    @GET("exercises/bodyPart/{bodyPart}?rapidapi-key=${Constant.MY_KEY}")
    suspend fun getExercisesByBodyPart(@Path("bodyPart") bodyPart: String): Response<List<ExerciseDTO>>

    @GET("exercises/target/{target}?rapidapi-key=${Constant.MY_KEY}")
    suspend fun getExercisesByTarget(@Path("target") target: String): Response<List<ExerciseDTO>>

    @GET("exercises?rapidapi-key=${Constant.MY_KEY}")
    suspend fun getAllExercises(): Response<List<ExerciseDTO>>


}