package com.fmu.rpgcharactercreation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.model.Character

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val characterImageView: ImageView = itemView.findViewById(R.id.imageViewCharacter)
    private val characterNameTextView: TextView = itemView.findViewById(R.id.textViewCharacterName)
    private val playerNameTextView: TextView = itemView.findViewById(R.id.textViewPlayerName)

    fun bind(character: Character) {
        characterNameTextView.text = character.characterName
        playerNameTextView.text = character.playerName

        characterImageView.setImageResource(R.drawable.character_profile)
    }
}
