package com.santos.marvel.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.santos.marvel.R
import com.santos.marvel.databinding.FragmentHomeBinding
import com.santos.marvel.home.HomeViewModel
import com.santos.marvel.home.MarvelCharactersAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelHomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val homeViewModel: HomeViewModel by viewModel()
    val startTime = System.currentTimeMillis()

    private lateinit var adapter: MarvelCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("FragmentDebug", "onCreateView chamado")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentDebug", "onViewCreated chamado")

        binding.loadingOverlay.visibility = View.VISIBLE

        adapter = MarvelCharactersAdapter(emptyList(),
            onItemClick = { character ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CharacterDetailFragment.newInstance(character))
                    .addToBackStack(null)
                    .commit()
            },
            onFavoriteClick = { character ->
                homeViewModel.toggleFavorite(character)
                adapter.notifyItemChanged(adapter.characters.indexOf(character))
                Toast.makeText(requireContext(), if (character.isFavorite) "Favoritado" else "Desfavoritado", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCharacters.adapter = adapter

        homeViewModel.fetchCharacters()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.characters.collect { marvelModel ->

                    val elapsed = System.currentTimeMillis() - startTime
                    if (elapsed < 10000) {
                        kotlinx.coroutines.delay(9000 - elapsed)}

                        binding.loadingOverlay.visibility = View.GONE

                        if (marvelModel == null) {
                            Log.e("AdapterUpdate", "MarvelModel é nulo")
                        } else if (marvelModel.data?.results.isNullOrEmpty()) {
                            Log.e("AdapterUpdate", "A lista de results está vazia ou nula")
                        } else {
                            val results = marvelModel.data?.results
                            Log.d("AdapterUpdate", "Atualizando adapter com ${results?.size} itens")
                            results?.let {
                                adapter.updateData(it)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}