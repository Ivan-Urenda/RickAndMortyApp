package com.ivo.rickandmortyapp.ui.view.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemCharacterBinding.bind(view)

    fun render(character: ResultsModel){
        binding.tvName.text = character.name
        binding.tvSpices.text = character.species
        binding.tvStatus.text = character.status
        binding.tvOrigin.text = character.origin.name
        binding.tvLocation.text = character.location.name
        Glide.with(binding.ivCharacter.context).load(character.image).into(binding.ivCharacter)

        itemView.setOnClickListener {
            Toast.makeText(binding.ivCharacter.context, character.id.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}