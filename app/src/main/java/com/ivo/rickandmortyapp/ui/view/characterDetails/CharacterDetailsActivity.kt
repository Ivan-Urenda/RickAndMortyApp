package com.ivo.rickandmortyapp.ui.view.characterDetails


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.databinding.ActivityCharacterDetailsBinding
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import com.ivo.rickandmortyapp.utils.LoadingDialog

class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding
    private val loading = LoadingDialog(this)

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        binding =ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loading.startLoading()

        val idCharacter = intent.extras?.getInt("id")
        val nameCharacter = intent.extras?.getString("name")
        val imageCharacter = intent.extras?.getString("image")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = nameCharacter
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Glide.with(this).load(imageCharacter).into(binding.ivCharacterDetail)

        characterViewModel.getCharacter("character/"+idCharacter.toString())
        characterViewModel.resultsModel.observe(this, Observer { character ->

            if (character != null){
                binding.tvName.text = character.name
                binding.tvSpecies.text = character.species
                binding.tvStatus.text = character.status
                binding.tvOrigin.text = character.origin.name
                binding.tvLocation.text = character.location.name
                binding.tvEpisodes.text = character.episode.size.toString()
                loading.isDismiss()
            }
            else
            {
                Toast.makeText(this, "Error, try again later", Toast.LENGTH_SHORT).show()
            }
            //Toast.makeText(this, character.name, Toast.LENGTH_SHORT).show()

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return false;
    }
}