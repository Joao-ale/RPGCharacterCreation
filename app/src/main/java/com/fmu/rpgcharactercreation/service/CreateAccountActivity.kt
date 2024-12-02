package com.fmu.rpgcharactercreation.service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import com.fmu.rpgcharactercreation.model.User
import com.fmu.rpgcharactercreation.R
import kotlinx.coroutines.launch

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val userDao = DatabaseBuilder.getInstance(this).userDao()

        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val createAccountButton: Button = findViewById(R.id.button_create_account)
        val login: Button = findViewById(R.id.login)

        login.setOnClickListener{
            lifecycleScope.launch{
                val intent = Intent(this@CreateAccountActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        createAccountButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                val user = User(email = email, password = password)
                userDao.insert(user)
                val savedUser = userDao.getUserByEmail(email)
                if (savedUser != null) {
                    Toast.makeText(this@CreateAccountActivity, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@CreateAccountActivity, "Erro ao salvar usuário!", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this@CreateAccountActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
