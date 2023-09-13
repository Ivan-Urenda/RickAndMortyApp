package com.ivo.rickandmortyapp.ui.view.charactersList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var llContainer: LinearLayout
    private lateinit var llLoading: LinearLayout

    private var page: Int = 1

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        llContainer = binding.llContainer
        llLoading = binding.llLoading


        initRecyclerView()

        binding.btnPrevPage.setOnClickListener { prevPage() }

        binding.btnNextPage.setOnClickListener { nextPage() }

        return binding.root
    }


    private fun initRecyclerView(){
        characterViewModel.getAllCharacters()
        characterViewModel.responseModel.observe(viewLifecycleOwner, Observer {

            if (it != null)
            {
                llLoading.isVisible = false
                llContainer.isVisible = true
                recyclerView = binding.recyclerCharacters
                recyclerView.layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = CharacterAdapter(it.results!!)
            }
            else
            {
                Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun nextPage(){
        if (page<42){

            llLoading.isVisible = true
            llContainer.isVisible = false
            page++
            characterViewModel.getCharactersByPage("character/?page=${page}")
            characterViewModel.responseModelByPage.observe(viewLifecycleOwner, Observer {
                if (it != null)
                {
                    llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = CharacterAdapter(it.results!!)
                }
                else
                {
                    Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
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
            characterViewModel.responseModelByPage.observe(viewLifecycleOwner, Observer {

                if (it != null)
                {
                    llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = CharacterAdapter(it.results!!)
                }
                else
                {
                    Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}