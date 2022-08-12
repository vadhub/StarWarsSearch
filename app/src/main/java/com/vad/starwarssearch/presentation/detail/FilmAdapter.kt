package com.vad.starwarssearch.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vad.starwarssearch.databinding.ItemCharacterBinding
import com.vad.starwarssearch.databinding.ItemFilmBinding

class FilmAdapter(private var listFilms: List<String>): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(itemView: ItemFilmBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.binding.titleFilm.text = listFilms.get(position)
    }

    override fun getItemCount() = listFilms.size

}