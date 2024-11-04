package com.fmu.rpgcharactercreation.service

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.dao.CharacterDao
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import com.fmu.rpgcharactercreation.enum.CharacterClass
import com.fmu.rpgcharactercreation.enum.Origin
import com.fmu.rpgcharactercreation.model.Attribute
import com.fmu.rpgcharactercreation.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateCharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        btnSubmit.setOnClickListener {
            val characterName = etCharacterName.text.toString()
            val playerName = etPlayerName.text.toString()
            val appearance = etAppearance.text.toString()
            val personality = etPersonality.text.toString()
            val history = etHistory.text.toString()
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
        CoroutineScope(Dispatchers.IO).launch {
            characterDao.insert(character)
        }
    }
}
