package com.example.examen

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateUserViewModel(val createUserRepository: SaveUserRepository) : ViewModel() {

    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()

    sealed class UiModel {
        class createUser(val success: Boolean): UiModel()
        object Loading: UiModel()
    }

    fun createUser(name: String, lastName: String, email: String) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.createUser( createUserRepository.createUser(name, lastName, email))
        }
        Handler().postDelayed(runnable, 3000)
    }
}