package com.ivo.rickandmortyapp.ui.view.charactersList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.data.models.ResultsModel
import com.ivo.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val characterList: List<ResultsModel>,
    private val onCharacterClick: (ResultsModel) -> Unit
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

        fun render(character: ResultsModel, ctxt: Context, onClick: (ResultsModel) -> Unit) = with(binding) {
            tvName.text = character.name
            tvSpices.text = ctxt.getString(R.string.species, character.species)
            tvStatus.text = ctxt.getString(R.string.status, character.status)
            tvOrigin.text = ctxt.getString(R.string.origin, character.origin.name)
            tvLocation.text = ctxt.getString(R.string.location, character.location.name)
            Glide.with(ivCharacter.context).load(character.image).into(binding.ivCharacter)
            root.setOnClickListener { onClick(character) }
        }
    }

}
