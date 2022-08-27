package com.vad.starwarssearch.presentation.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vad.starwarssearch.R
import com.vad.starwarssearch.databinding.FragmentFavoriteBinding
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.character.CharacterAdapter

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val characterRepository = CharacterRepository(AppDatabase.getDatabase(context!!.applicationContext).characterDao())
        //val viewModelFactory = CharacterViewModelFactory(characterRepository)
       // viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
        characterAdapter = CharacterAdapter(viewModel)
        binding.myRecyclerview.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        characterAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }

        viewModel.getCharacters()

        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.differ.submitList(it)
        }
    }
}