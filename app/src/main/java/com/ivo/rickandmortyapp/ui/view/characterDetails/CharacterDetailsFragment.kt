package com.ivo.rickandmortyapp.ui.view.characterDetails

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import com.ivo.rickandmortyapp.databinding.FragmentCharacterDetailsBinding
import com.ivo.rickandmortyapp.ui.viewmodel.CharacterViewModel
import com.ivo.rickandmortyapp.utils.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressBar: ProgressBar

    val args: CharacterDetailsFragmentArgs by navArgs()

    private val characterViewModel: CharacterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE

        val id = args.id
        val name = args.name
        val image = args.image

        Glide.with(this).load(image).into(binding.ivCharacterDetail)
        binding.toolbar.title = name

        characterViewModel.getCharacter("character/"+id.toString())
        characterViewModel.resultsModel.observe(viewLifecycleOwner, Observer { character ->

            if (character != null){
                binding.tvName.text = character.name
                binding.tvSpecies.text = character.species
                binding.tvStatus.text = character.status
                binding.tvOrigin.text = character.origin.name
                binding.tvLocation.text = character.location.name
                binding.tvEpisodes.text = character.episode.size.toString()
                progressBar.visibility = View.GONE

            }
            else
            {
                Toast.makeText(activity, "Error, try again later", Toast.LENGTH_SHORT).show()
            }

        })
    }
}