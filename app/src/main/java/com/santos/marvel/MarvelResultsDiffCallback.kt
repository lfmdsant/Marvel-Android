package com.santos.marvel

import androidx.recyclerview.widget.DiffUtil
import com.santos.marvel.data.MarvelResults

class MarvelResultsDiffCallback (
    private val oldList: List<MarvelResults>,
    private val newList: List<MarvelResults>) : DiffUtil.Callback()
{
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}