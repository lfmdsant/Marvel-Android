package com.santos.marvel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.santos.marvel.R
import com.santos.marvel.databinding.FragmentFavoritesBinding
import com.santos.marvel.home.HomeViewModel
import com.santos.marvel.home.MarvelCharactersAdapter
import kotlinx.coroutines.launch

class FavoritesFragment: Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var homeViewModel: HomeViewModel? = null

    private lateinit var adapter: MarvelCharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MarvelCharactersAdapter(emptyList(),
            onItemClick = { character ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, CharacterDetailFragment.newInstance(character))
                    .addToBackStack(null)
                    .commit()
            },
            onFavoriteClick = { character ->
                homeViewModel?.toggleFavorite(character)
                adapter.notifyItemChanged(adapter.characters.indexOf(character))
            }
        )
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavorites.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel?.favorites?.collect { favoritesList ->
                    android.util.Log.d("FavoritesFragment", "Favorites coletados: ${favoritesList.size} itens")
                    adapter.updateData(favoritesList)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(homeViewModel: HomeViewModel) : FavoritesFragment {
            val fragment = FavoritesFragment()
                fragment.homeViewModel = homeViewModel
            return fragment
        }
    }
}