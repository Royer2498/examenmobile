package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var createUserViewModel: CreateUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createUserViewModel = CreateUserViewModel(SaveUserRepository())
        createUserViewModel.model.observe(this, Observer(::updateUi))

        create_user.setOnClickListener {
            createUserViewModel.createUser(name.text.toString(), lastName.text.toString(), email.text.toString())
        }
    }

    private fun updateUi(model: CreateUserViewModel.UiModel?) {
        progressBar.visibility = if ( model is CreateUserViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is CreateUserViewModel.UiModel.createUser -> validarCreateUser(model.success)
        }
    }

    private fun validarCreateUser( resp: Boolean) {
        if( resp) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Mensaje de la app")
            builder.setMessage("Todo fue un exito")
            builder.show()

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Mensaje de la app")
            builder.setMessage("Ocurrio un error lo siento")
            builder.show()
        }

    }
}
