package com.example.data.network.api

import com.example.common.Constant
import com.example.data.network.models.ExerciseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExercisesService {

//    @GET("exercises/name/{bodyPart}?rapidapi-key=${Constant.MY_KEY}")
//    suspend fun getExercisesByBodyPart(@Path("bodyPart") bodyPart: String): Response<ExerciseDTO>
//
//    @GET("exercises/name/{target}?rapidapi-key=${Constant.MY_KEY}")
//    suspend fun getExercisesByTarget(@Path("target") target: String): Response<ExerciseDTO>
//
//    @GET("exercises/name/{equipment}?rapidapi-key=${Constant.MY_KEY}")
//    suspend fun getExercisesByEquipment(@Path("equipment") equipment: String): Response<ExerciseDTO>

    @GET("exercises?rapidapi-key=${Constant.MY_KEY}")
    suspend fun getAllExercises(): Response<List<ExerciseDTO>>


}