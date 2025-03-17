package com.santos.marvel.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santos.marvel.MarvelResultsDiffCallback
import com.santos.marvel.R
import com.santos.marvel.data.MarvelResults
import com.santos.marvel.databinding.ItemMarvelCharacterBinding

class MarvelCharactersAdapter(
    var characters: List<MarvelResults>,
    private val onItemClick: (MarvelResults) -> Unit,
    private val onFavoriteClick: (MarvelResults) -> Unit
) : RecyclerView.Adapter<MarvelCharactersAdapter.MarvelCharacterViewHolder>() {

    inner class MarvelCharacterViewHolder(private val binding: ItemMarvelCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: MarvelResults) {
            binding.textViewName.text = character.name
            Glide.with(binding.imageViewThumbnail.context)
                .load(character.thumbnail?.thumbnailURL)
                .placeholder(android.R.color.darker_gray)
                .into(binding.imageViewThumbnail)

            val favoriteIcon = if (character.isFavorite)
                R.drawable.ic_star_filled
            else
                R.drawable.ic_star_outline
            binding.buttonFavorite.setImageResource(favoriteIcon)

            binding.root.setOnClickListener {
                onItemClick(character)
            }
            binding.buttonFavorite.setOnClickListener {
                onFavoriteClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharactersAdapter.MarvelCharacterViewHolder {
        val binding = ItemMarvelCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MarvelCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    fun updateData(newCharacters: List<MarvelResults>) {
        val diffCallback = MarvelResultsDiffCallback(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters = newCharacters
        diffResult.dispatchUpdatesTo(this)
    }
}