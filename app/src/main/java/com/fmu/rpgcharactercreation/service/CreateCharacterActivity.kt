package com.fmu.rpgcharactercreation.service

import androidx.appcompat.app.AppCompatActivity
import com.fmu.rpgcharactercreation.enum.CharacterClass
import com.fmu.rpgcharactercreation.enum.Origin
import com.fmu.rpgcharactercreation.model.Attribute
import com.fmu.rpgcharactercreation.model.Skill

class CreateCharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        val etCharacterName: EditText = findViewById(R.id.etCharacterName)
        val etPlayerName: EditText = findViewById(R.id.etPlayerName)
        val etAppearance: EditText = findViewById(R.id.etAppearance)
        val etPersonality: EditText = findViewById(R.id.etPersonality)
        val etHistory: EditText = findViewById(R.id.etHistory)
        val spinnerOrigin: Spinner = findViewById(R.id.spinnerOrigin)
        val spinnerClass: Spinner = findViewById(R.id.spinnerClass)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        // Inicializar os spinners com enum
        val originAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Origin.values())
        spinnerOrigin.adapter = originAdapter

        val classAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, CharacterClass.values())
        spinnerClass.adapter = classAdapter

        btnSubmit.setOnClickListener {
            val characterName = etCharacterName.text.toString()
            val playerName = etPlayerName.text.toString()
            val appearance = etAppearance.text.toString()
            val personality = etPersonality.text.toString()
            val history = etHistory.text.toString()
            val origin = spinnerOrigin.selectedItem as Origin
            val characterClass = spinnerClass.selectedItem as CharacterClass

            val attributes = listOf(
                Attribute(
                    "Força", 1
                ),
                Attribute(
                    "Agilidade", 1
                ),
                Attribute(
                    "Intelecto", 1
                ),
                Attribute(
                    "Vigor", 1
                ),
                Attribute(
                    "Presença", 1
                )
            )

            val character = Character(
                characterName = characterName,
                playerName = playerName,
                appearance = appearance,
                personality = personality,
                history = history,
                attributes = attributes,
                origin = origin,
                skills = skills,
                characterClass = characterClass
            )

            // Faça algo com o objeto Character, como salvar no banco de dados
            saveCharacter(character)
        }
    }

//    private fun saveCharacter(character: Character) {
//        // Lógica para salvar no banco de dados ou enviar para API
//    }
}
