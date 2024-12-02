package com.fmu.rpgcharactercreation.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.dao.CharacterDao
import com.fmu.rpgcharactercreation.dataBase.DatabaseBuilder
import com.fmu.rpgcharactercreation.enums.CharacterClass
import com.fmu.rpgcharactercreation.enums.Origin
import com.fmu.rpgcharactercreation.model.Attribute
import com.fmu.rpgcharactercreation.model.Character
import kotlinx.coroutines.launch

class CreateCharacterActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var selectImageButton: Button

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            imageView.setImageURI(uri)
        } else {
            Toast.makeText(this, "Nenhuma imagem selecionada.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        val characterDao = DatabaseBuilder.getInstance(this).characterDao()

        imageView = findViewById(R.id.imageViewCharacter)
        selectImageButton = findViewById(R.id.btnSelectImage)

        selectImageButton.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }


        val etCharacterName: EditText = findViewById(R.id.etCharacterName)
        val etPlayerName: EditText = findViewById(R.id.etPlayerName)
        val etAppearance: EditText = findViewById(R.id.etAppearance)
        val etPersonality: EditText = findViewById(R.id.etPersonality)
        val etHistory: EditText = findViewById(R.id.etHistory)
        val spinnerOrigin: Spinner = findViewById(R.id.spinnerOrigin)
        val spinnerClass: Spinner = findViewById(R.id.spinnerClass)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        imageView = findViewById(R.id.imageViewCharacter)
        selectImageButton = findViewById(R.id.btnSelectImage)

        val etStrength: EditText = findViewById(R.id.etStrength)
        val etAgility: EditText = findViewById(R.id.etAgility)
        val etIntellect: EditText = findViewById(R.id.etIntellect)
        val etVigor: EditText = findViewById(R.id.etVigor)
        val etPresence: EditText = findViewById(R.id.etPresence)

        val originAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            Origin.entries.toTypedArray()
        )
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigin.adapter = originAdapter

        val classAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            CharacterClass.entries.toTypedArray()
        )
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

            val strength = (etStrength.text.toString().toIntOrNull() ?: 1).coerceIn(1, 10)
            val agility = (etAgility.text.toString().toIntOrNull() ?: 1).coerceIn(1, 10)
            val intellect = (etIntellect.text.toString().toIntOrNull() ?: 1).coerceIn(1, 10)
            val vigor = (etVigor.text.toString().toIntOrNull() ?: 1).coerceIn(1, 10)
            val presence = (etPresence.text.toString().toIntOrNull() ?: 1).coerceIn(1, 10)


            val attributes = listOf(
                Attribute("Força", strength),
                Attribute("Agilidade", agility),
                Attribute("Intelecto", intellect),
                Attribute("Vigor", vigor),
                Attribute("Presença", presence)
            )
            val userId = LoginActivity.SessionManager.userId

            val character = Character(
                characterName = characterName,
                playerName = playerName,
                appearance = appearance,
                personality = personality,
                history = history,
                attributes = attributes,
                origin = origin,
                characterClass = characterClass,
                imageUri = selectedImageUri?.toString(),
                userId = userId
            )

            saveCharacter(character, characterDao)

            val intent = Intent(this@CreateCharacterActivity, CharacterListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveCharacter(character: Character, characterDao: CharacterDao) {
        lifecycleScope.launch {
            characterDao.insert(character)
            Toast.makeText(this@CreateCharacterActivity, "Personagem criado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}
