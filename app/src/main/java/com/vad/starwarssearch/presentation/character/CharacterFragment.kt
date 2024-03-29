package com.vad.starwarssearch.presentation.character

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vad.starwarssearch.R
import com.vad.starwarssearch.data.repository.CharacterRepository
import com.vad.starwarssearch.databinding.FragmentCharacterBinding
import com.vad.starwarssearch.domain.HandleError
import com.vad.starwarssearch.presentation.CharacterSearchViewModel
import com.vad.starwarssearch.presentation.CharacterSearchViewModelFactory
import com.vad.starwarssearch.presentation.MainActivity
import java.util.*
import javax.inject.Inject

class CharacterFragment : Fragment(), HandleError {

    private lateinit var binding: FragmentCharacterBinding
    private lateinit var viewModel: CharacterSearchViewModel
    private lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var repository: CharacterRepository

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

        val factory = CharacterSearchViewModelFactory(repository, this)
        viewModel = ViewModelProvider(this, factory).get(CharacterSearchViewModel::class.java)

        characterAdapter = CharacterAdapter(viewModel)
        binding.myRecyclerviewSearch.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.differ.submitList(it)
        }

        characterAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("character", it)
            }
            findNavController().navigate(R.id.action_characterFragment_to_detailFragment, bundle)
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

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase(Locale.getDefault())
                viewModel.searchCharacters(searchText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun handle(error: String) {
        Snackbar.make(binding.characterFragmentLL, error, Snackbar.LENGTH_SHORT).show()
    }
}