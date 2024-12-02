package com.fmu.rpgcharactercreation.service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.dao.UserDao
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    object SessionManager {
        var userId: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userDao = DatabaseBuilder.getInstance(this).userDao()

        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val loginButton: Button = findViewById(R.id.loggin_button)
        val createAccountButton: Button = findViewById(R.id.new_account_button)

        createAccountButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                val user = userDao.getUserByEmail(email)
                if (user != null && user.password == password) {
                    SessionManager.userId = user.id

                    val intent = Intent(this@LoginActivity, CharacterListActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Usuário ou senha inválidos. Verifique suas credenciais.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
