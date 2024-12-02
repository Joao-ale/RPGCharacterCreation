package com.fmu.rpgcharactercreation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fmu.rpgcharactercreation.R
import com.fmu.rpgcharactercreation.model.CharacterView

class CharacterAdapter(private val characters: List<CharacterView>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterNameTextView: TextView = view.findViewById(R.id.characterName)
        val playerNameTextView: TextView = view.findViewById(R.id.textViewPlayerName)
        val characterImageView: ImageView = view.findViewById(R.id.imageViewCharacter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.characterNameTextView.text = character.characterName
        holder.playerNameTextView.text = character.playerName
        holder.characterImageView.setImageResource(R.drawable.character_profile)
    }

    override fun getItemCount(): Int = characters.size
}
