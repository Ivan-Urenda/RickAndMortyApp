package com.ivo.rickandmortyapp.ui.view.characterDetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.data.models.CharacterResponse
import com.ivo.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.ivo.rickandmortyapp.domain.characters.model.CharacterModel
import com.ivo.rickandmortyapp.ui.view.charactersList.CharacterListFragmentDirections
import com.ivo.rickandmortyapp.ui.view.charactersList.adapter.CharacterAdapter
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import com.ivo.rickandmortyapp.utils.loadImageFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    val args: CharacterDetailsFragmentArgs by navArgs()

    private val characterViewModel: CharacterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.getCharacterDetail(args.id)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        characterViewModel.characterDetail.observe(viewLifecycleOwner) { setCharacter(it) }
        characterViewModel.isError.observe(viewLifecycleOwner) {
            binding.textViewError.visibility = View.VISIBLE
        }
        characterViewModel.loading.observe(viewLifecycleOwner) { progressBar(it) }
    }

    private fun setCharacter(character: CharacterModel) {
        binding.run {
            ivCharacterDetail.loadImageFromUrl(character.image)
            tvName.text = getString(R.string.name, character.name)
            tvSpecies.text = getString(R.string.species, character.specie)
            tvStatus.text = getString(R.string.status, character.status)
            tvOrigin.text = getString(R.string.origin, character.origin)
            tvLocation.text = getString(R.string.location, character.location)
            tvEpisodes.text = getString(R.string.episodes, character.episodes)
        }
    }

    private fun progressBar(isLoading: Boolean) = with(binding.progressBar) {
        this.isVisible = isLoading
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}