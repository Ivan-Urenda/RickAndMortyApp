package com.ivo.rickandmortyapp.ui.view.charactersList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ivo.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private var page: Int = 1

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.getAllCharacters()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        characterViewModel.characters.observe(viewLifecycleOwner) { setCharacters(it) }
        characterViewModel.isError.observe(viewLifecycleOwner) { binding.textViewError.visibility = View.VISIBLE}
        characterViewModel.loading.observe(viewLifecycleOwner) { progressShimmer(it) }
    }

    private fun progressShimmer(isLoading: Boolean) = with(binding.shimmerCharactersLoading) {
        this.isVisible = isLoading
        if (isLoading) {
            this.startShimmer()
        } else {
            this.stopShimmer()
        }
    }

    private fun setCharacters(characters: List<CharacterModel>) {
        binding.characterSuccessViewGroup.isVisible = true
        binding.recyclerCharacters.apply {
            adapter = CharacterAdapter(characterList = characters) {
                val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(it.id)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*private fun initRecyclerView(){
        characterViewModel.getAllCharacters()
        characterViewModel.mainCharactersResponse.observe(viewLifecycleOwner, Observer {

            if (it != null)
            {
                *//*llLoading.isVisible = false
                llContainer.isVisible = true
                recyclerView = binding.recyclerCharacters
                recyclerView.layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = CharacterAdapter(it.results!!) { character ->
                    val nvaDirections = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(character.id, character.name, character.image)
                    findNavController().navigate(nvaDirections)
                }*//*
            }
            else
            {
                Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
    /*private fun nextPage(){
        if (page<42){

            page++
            characterViewModel.getCharactersByPage("character/?page=${page}")
            characterViewModel.mainCharactersResponseByPage.observe(viewLifecycleOwner, Observer {
                if (it != null)
                {
                    *//*llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = CharacterAdapter(it.results!!)  {
                        Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
                    }*//*
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

            *//*llLoading.isVisible = true
            llContainer.isVisible = false*//*
            page--
            characterViewModel.getCharactersByPage("character/?page=${page}")
            characterViewModel.mainCharactersResponseByPage.observe(viewLifecycleOwner, Observer {

                if (it != null)
                {
                    *//*llLoading.isVisible = false
                    llContainer.isVisible = true
                    recyclerView = binding.recyclerCharacters
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = CharacterAdapter(it.results!!) {
                        Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
                    }*//*
                }
                else
                {
                    Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }*/
}