package com.example.gymproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.models.Exercise
import com.example.domain.use_cases.AllExercisesUseCase
import com.example.domain.use_cases.ExercisesByBodyPartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val allExercisesUseCase: AllExercisesUseCase,
    private val exercisesByBodyPartUseCase: ExercisesByBodyPartUseCase
): ViewModel() {

    val searchQuery = MutableLiveData<String>()
    val loaderState = MutableLiveData<Boolean>()
    val searchError = MutableLiveData<Boolean>()
    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>>
        get() = _exercises

    init {
        getAllExercises()
    }

    private fun getAllExercises() {
        allExercisesUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    Log.d("exercisesState", "Loading")
                    loaderState.postValue(false)
                }
                is Resource.Success -> {
                    _exercises.value = it.data!!
                    loaderState.postValue(true)
                }
                is Resource.Error -> {
                    Log.e("exercisesState", "Error getting all exercises")
                    loaderState.postValue(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getExercisesByBodyPart() {
        exercisesByBodyPartUseCase.bodyPart = searchQuery.value!!
        exercisesByBodyPartUseCase().onEach {
            when(it) {
                is Resource.Loading -> {
                    Log.d("exercisesState", "Loading")
                    loaderState.postValue(false)
                    searchError.postValue(false)
                }
                is Resource.Success -> {
                    _exercises.value = it.data!!
                    loaderState.postValue(true)
                    searchError.postValue(false)
                }
                is Resource.Error -> {
                    Log.e("exercisesState", "Error getting exercises by bodyPart")
                    loaderState.postValue(true)
                    searchError.postValue(true)
                }
            }
        }.launchIn(viewModelScope)
    }

}