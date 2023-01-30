package com.example.data.mappers

import com.example.data.network.models.ExerciseDTO
import com.example.domain.models.Exercise

fun List<ExerciseDTO>.toDomain(): List<Exercise> {
    return map {
        Exercise(
            bodyPart = it.bodyPart,
            equipment = it.equipment,
            gifUrl = it.gifUrl,
            id = it.id,
            name = it.name,
            target = it.target
        )
    }
}