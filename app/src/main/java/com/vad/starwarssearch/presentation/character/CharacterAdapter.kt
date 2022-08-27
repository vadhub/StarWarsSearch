package com.vad.starwarssearch.presentation.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.databinding.ItemCharacterBinding
import com.vad.starwarssearch.presentation.CharacterViewModel
import javax.inject.Inject

class CharacterAdapter @Inject constructor(private val viewModel: CharacterViewModel) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding = itemBinding
    }

    private val differCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = differ.currentList[position]

        holder.binding.apply {
            nameCharacter.text = character.name
            root.setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
            if (character.isFavorite) {
                favoriteImg.setImageResource(R.drawable.ic_baseline_favorite_red_24)
            }

            favoriteImg.setOnClickListener {
                if (character.isFavorite) {
                    favoriteImg.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    viewModel.deleteCharacter(character)
                } else {
                    favoriteImg.setImageResource(R.drawable.ic_baseline_favorite_red_24)
                    character.setFavorite()
                    viewModel.saveCharacter(character)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Character) -> Unit)? = null

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }
}