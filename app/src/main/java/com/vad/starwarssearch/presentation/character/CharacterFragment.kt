package com.vad.starwarssearch.presentation.character

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vad.starwarssearch.App
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.entity.Character
import com.vad.starwarssearch.databinding.FragmentCharacterBinding
import com.vad.starwarssearch.presentation.CharacterViewModel
import com.vad.starwarssearch.presentation.MainActivity
import java.util.*

class CharacterFragment : Fragment() {

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
        viewModel = App(this).viewModel
        setupRecyclerView()

        characterAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(R.id.action_characterFragment_to_detailFragment, bundle)
        }
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