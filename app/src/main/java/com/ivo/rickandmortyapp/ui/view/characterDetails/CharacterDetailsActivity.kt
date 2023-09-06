package com.ivo.rickandmortyapp.ui.view.characterDetails

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.databinding.ActivityCharacterDetailsBinding
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        binding =ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idCharacter = intent.extras?.getInt("id")
        val nameCharacter = intent.extras?.getString("name")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = nameCharacter
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        characterViewModel.getCharacter("character/"+idCharacter.toString())
        characterViewModel.resultsModel.observe(this, Observer {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            Glide.with(this).load(it.image).into(binding.ivCharacterDetail)

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return false;
    }
}