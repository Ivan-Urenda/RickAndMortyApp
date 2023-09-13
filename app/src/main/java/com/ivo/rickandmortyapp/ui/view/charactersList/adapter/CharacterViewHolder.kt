package com.ivo.rickandmortyapp.ui.view.charactersList.adapter

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemCharacterBinding.bind(view)

    fun render(character: ResultsModel){
        binding.tvName.text = character.name
        binding.tvSpices.text = Html.fromHtml("Spices: <b>${character.species}</b>")
        binding.tvStatus.text = Html.fromHtml("Status: <b>${character.status}</b>")
        binding.tvOrigin.text = Html.fromHtml("Origin: <b>${character.origin.name}</b>")
        binding.tvLocation.text = Html.fromHtml("Location: <b>${character.location.name}</b>")
        Glide.with(binding.ivCharacter.context).load(character.image).into(binding.ivCharacter)

    }
}