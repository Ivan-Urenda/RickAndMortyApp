package com.ivo.rickandmortyapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.data.models.ResultsModel

class CharacterAdapter(private val characterList:List<ResultsModel>) : RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characterList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = characterList.size
}