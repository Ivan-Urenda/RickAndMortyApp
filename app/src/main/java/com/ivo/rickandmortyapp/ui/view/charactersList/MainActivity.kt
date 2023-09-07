package com.ivo.rickandmortyapp.ui.view.charactersList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivo.rickandmortyapp.databinding.ActivityMainBinding
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import com.ivo.rickandmortyapp.utils.LoadingDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var llContainer: LinearLayout
    private lateinit var llLoading: LinearLayout

    private var page: Int = 1

    private val characterViewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llContainer = binding.llContainer
        llLoading = binding.llLoading


        initRecyclerView()

        binding.btnPrevPage.setOnClickListener { prevPage() }

        binding.btnNextPage.setOnClickListener { nextPage() }


    }

    private fun initRecyclerView(){
        characterViewModel.getAllCharacters()
        characterViewModel.responseModel.observe(this, Observer {

            if (it != null)
            {
                llLoading.isVisible = false
                llContainer.isVisible = true
                recyclerView = binding.recyclerCharacters
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = CharacterAdapter(it.results!!)
            }
            else
            {
                Toast.makeText(this, "Error, try again later", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun nextPage(){
        if (page<42){

            llLoading.isVisible = true
            llContainer.isVisible = false
            page++
            characterViewModel.getCharactersByPage("character/?page=${page}")
            characterViewModel.responseModelByPage.observe(this, Observer {
                if (it != null)
                {
                    llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = CharacterAdapter(it.results!!)
                }
                else
                {
                    Toast.makeText(this, "Error, try again later", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun prevPage(){
        if (page>1){

            llLoading.isVisible = true
            llContainer.isVisible = false
            page--
            characterViewModel.getCharactersByPage("character/?page=${page}")
            characterViewModel.responseModelByPage.observe(this, Observer {

                if (it != null)
                {
                    llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = CharacterAdapter(it.results!!)
                }
                else
                {
                    Toast.makeText(this, "Error, try again later", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}