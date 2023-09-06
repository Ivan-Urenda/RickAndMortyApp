package com.ivo.rickandmortyapp.ui.view.charactersList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivo.rickandmortyapp.databinding.ActivityMainBinding
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val characterViewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterViewModel.getAllCharacters()

        characterViewModel.responseModel.observe(this, Observer {
            val recyclerView = binding.recyclerCharacters
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = CharacterAdapter(it.results!!)
        })
    }
}