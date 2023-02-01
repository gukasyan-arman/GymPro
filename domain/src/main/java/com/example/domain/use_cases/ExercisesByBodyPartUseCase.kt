package com.example.domain.use_cases

import com.example.common.Resource
import com.example.domain.models.Exercise
import com.example.domain.repository.ExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//class ExercisesByBodyPartUseCase @Inject constructor(private val exercisesRepository: ExercisesRepository){
//    operator fun invoke(): Flow<Resource<List<Exercise>>> = flow {
//        emit(Resource.Loading(null))
//        try {
//            val response = exercisesRepository.getExercisesByBodyPart()
//            emit(Resource.Success(data = response))
//        } catch (e: Exception) {
//            emit(Resource.Error("error occurred"))
//        }
//    }
//}