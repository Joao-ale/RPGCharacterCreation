package com.fmu.rpgcharactercreation.service

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.dao.CharacterDao
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import com.fmu.rpgcharactercreation.enum.CharacterClass
import com.fmu.rpgcharactercreation.enum.Origin
import com.fmu.rpgcharactercreation.model.Attribute
import com.fmu.rpgcharactercreation.model.Character
import kotlinx.coroutines.launch

class CreateCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        val characterDao = DatabaseBuilder.getInstance(this).characterDao()

        val etCharacterName: EditText = findViewById(R.id.etCharacterName)
        val etPlayerName: EditText = findViewById(R.id.etPlayerName)
        val etAppearance: EditText = findViewById(R.id.etAppearance)
        val etPersonality: EditText = findViewById(R.id.etPersonality)
        val etHistory: EditText = findViewById(R.id.etHistory)
        val spinnerOrigin: Spinner = findViewById(R.id.spinnerOrigin)
        val spinnerClass: Spinner = findViewById(R.id.spinnerClass)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        val etStrength: EditText = findViewById(R.id.etStrength)
        val etAgility: EditText = findViewById(R.id.etAgility)
        val etIntellect: EditText = findViewById(R.id.etIntellect)
        val etVigor: EditText = findViewById(R.id.etVigor)
        val etPresence: EditText = findViewById(R.id.etPresence)

        val originAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Origin.values())
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigin.adapter = originAdapter

        val classAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, CharacterClass.values())
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClass.adapter = classAdapter

        btnSubmit.setOnClickListener {
            val characterName = etCharacterName.text.toString().trim()
            val playerName = etPlayerName.text.toString().trim()
            val appearance = etAppearance.text.toString().trim()
            val personality = etPersonality.text.toString().trim()
            val history = etHistory.text.toString().trim()

            if (characterName.isEmpty() || playerName.isEmpty() || appearance.isEmpty() || personality.isEmpty() || history.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val origin = spinnerOrigin.selectedItem as Origin
            val characterClass = spinnerClass.selectedItem as CharacterClass

            val strength = etStrength.text.toString().toIntOrNull() ?: 1
            val agility = etAgility.text.toString().toIntOrNull() ?: 1
            val intellect = etIntellect.text.toString().toIntOrNull() ?: 1
            val vigor = etVigor.text.toString().toIntOrNull() ?: 1
            val presence = etPresence.text.toString().toIntOrNull() ?: 1

            val attributes = listOf(
                Attribute("Força", strength),
                Attribute("Agilidade", agility),
                Attribute("Intelecto", intellect),
                Attribute("Vigor", vigor),
                Attribute("Presença", presence)
            )

            val character = Character(
                characterName = characterName,
                playerName = playerName,
                appearance = appearance,
                personality = personality,
                history = history,
                attributes = attributes,
                origin = origin,
                characterClass = characterClass
            )

            saveCharacter(character, characterDao)
        }
    }

    private fun saveCharacter(character: Character, characterDao: CharacterDao) {
        lifecycleScope.launch {
            characterDao.insert(character)
            Toast.makeText(this@CreateCharacterActivity, "Personagem criado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}
