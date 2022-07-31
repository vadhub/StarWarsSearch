package com.vad.starwarssearch.presentation.character

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.databinding.FragmentCharacterBinding
import com.vad.starwarssearch.domain.Resource
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.MainActivity

class CharacterFragment : Fragment() {

    private val TAG: String = "starWarsError"
    private lateinit var binding: FragmentCharacterBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        val data = mutableListOf<Character>()
        viewModel.characters.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { characterList ->
                        data.addAll(characterList.results)
                        characterAdapter.differ.submitList(data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "error occured: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        characterAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(R.id.action_characterFragment_to_detailFragment, bundle)
        }

    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter()
        binding.myRecyclerviewSearch.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}