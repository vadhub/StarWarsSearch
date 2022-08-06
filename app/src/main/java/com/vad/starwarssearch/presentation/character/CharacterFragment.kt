package com.vad.starwarssearch.presentation.character

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.databinding.FragmentCharacterBinding
import com.vad.starwarssearch.domain.Resource
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.MainActivity
import java.util.*
import kotlin.collections.ArrayList

class CharacterFragment : Fragment() {

    private val TAG: String = "starWarsError"
    private lateinit var binding: FragmentCharacterBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

//        viewModel.characters.observe(viewLifecycleOwner, Observer { response ->
//            when (response) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    response.data?.let { characterList ->
//                        characterAdapter.differ.submitList(characterList.results)
//                    }
//                }
//                is Resource.Error -> {
//                    hideProgressBar()
//                    response.message?.let { message ->
//                        Log.e(TAG, "error occured: $message")
//                    }
//                }
//
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//            }
//        })

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = ((context as MainActivity).supportActionBar?.themedContext
            ?: context)?.let { SearchView(it) }
        menu.findItem(R.id.search_character).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        val listCharacters = mutableListOf<Character>()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    listCharacters.clear()
                    characterAdapter.differ.currentList.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                            listCharacters.add(it)
                        }
                    }

                    if (listCharacters.isNotEmpty()) {
                        characterAdapter.differ.submitList(listCharacters)
                    }
                }
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
}