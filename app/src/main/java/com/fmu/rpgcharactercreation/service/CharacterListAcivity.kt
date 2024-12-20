package com.fmu.rpgcharactercreation.service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.adapter.CharacterAdapter
import com.fmu.rpgcharactercreation.converters.toOrigin
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import com.fmu.rpgcharactercreation.model.Character
import com.fmu.rpgcharactercreation.model.CharacterView
import com.fmu.rpgcharactercreation.service.LoginActivity.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListActivity : AppCompatActivity() {
    object SessionManager {
        var userId: Int = LoginActivity.SessionManager.userId
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val createCharacterButton: Button = findViewById(R.id.button_add_character)

        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.Main).launch {
            val userId = SessionManager.userId
            val characters = getCharacters(userId)
            Toast.makeText(this@CharacterListActivity, "id recuperado: ${userId}", Toast.LENGTH_SHORT).show()
            loadCharacters(characters)
        }

        createCharacterButton.setOnClickListener {
            val intent = Intent(this@CharacterListActivity, CreateCharacterActivity::class.java)
            startActivity(intent)
        }
    }

    private suspend fun loadCharacters(characters: List<CharacterView>) {
        withContext(Dispatchers.Main) {
            val adapter = CharacterAdapter(characters)
            findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        }
    }


    private suspend fun getCharacters(userId: Int): List<CharacterView> {
        val characterDao = DatabaseBuilder.getInstance(this@CharacterListActivity).characterDao()
        val characterList = characterDao.getCharactersByUser(userId)
        return characterList.map { character ->
            CharacterView(
                characterId = character.id,
                characterName = character.characterName,
                playerName = character.playerName,
                origin = character.origin.name,
                characterClass = character.characterClass.name,
                imageUri = character.imageUri,
                userName = "User"
            )
        }
    }
}
