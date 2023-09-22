package com.ivo.rickandmortyapp.ui.view.charactersList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.databinding.ItemCharacterBinding
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.utils.loadImageFromUrl

class CharacterAdapter(
    private val characterList: List<CharacterModel>,
    private val onCharacterClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        val binding = ItemCharacterBinding.bind(itemView)
        return CharacterViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterList[position]
        mContext?.let {
            holder.render(character = item, ctxt = it, onClick = onCharacterClick)
        }
    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacterViewHolder(val binding: ItemCharacterBinding):RecyclerView.ViewHolder(binding.root) {

        fun render(character: CharacterModel, ctxt: Context, onClick: (CharacterModel) -> Unit) = with(binding) {
            tvName.text = character.name
            tvSpices.text = ctxt.getString(R.string.species, character.specie)
            tvStatus.text = ctxt.getString(R.string.status, character.status)
            tvOrigin.text = ctxt.getString(R.string.origin, character.origin)
            tvLocation.text = ctxt.getString(R.string.location, character.location)
            ivCharacter.loadImageFromUrl(character.image)
            root.setOnClickListener { onClick(character) }
        }
    }

}
