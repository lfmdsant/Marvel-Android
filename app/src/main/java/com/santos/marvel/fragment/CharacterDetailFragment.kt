package com.santos.marvel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.santos.marvel.data.MarvelResults
import com.santos.marvel.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private var character: MarvelResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_CHARACTER, MarvelResults::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(ARG_CHARACTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        character?.let {
            binding.textViewCharacterName.text = it.name?: "Nome não disponível"
            binding.textViewCharacterDescription.text = it.description?: "Descrição não disponível"
            binding.textViewComics.text = "Comics: " + (character?.comics?.items?.joinToString { it.name.toString() } ?: "N/A")
            binding.textViewEvents.text = "Events: " + (character?.events?.items?.joinToString { it.name.toString() } ?: "N/A")
            binding.textViewModified.text = "Modificado: " + (character?.modified ?: "N/A")
            binding.textViewResourceURI.text = "Resource URI: " + (character?.resourceURI ?: "N/A")
            Glide.with(requireContext())
                .load(it.thumbnail?.thumbnailURL)
                .into(binding.imageViewCharacter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_CHARACTER = "character_arg"

        fun newInstance(character: MarvelResults): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val args = Bundle().apply {
                putParcelable(ARG_CHARACTER, character)
            }
            fragment.arguments = args
            return fragment
        }
    }
}