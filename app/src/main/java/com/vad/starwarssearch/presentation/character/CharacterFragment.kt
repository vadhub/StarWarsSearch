package com.vad.starwarssearch.presentation.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vad.starwarssearch.R
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.MainActivity

class CharacterFragment : Fragment() {

    private lateinit var viewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }
}