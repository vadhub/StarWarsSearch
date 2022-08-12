package com.vad.starwarssearch.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character: Character = arguments?.getSerializable("character") as Character

        binding.name.text = character.name
        binding.birthday.text = character.birthYear
        binding.gender.text = character.gender
        val filmAdapter = FilmAdapter(character.films)
        binding.myRecyclerviewFilms.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = filmAdapter
        }


    }

}