package com.yasserakbbach.noteappcleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yasserakbbach.noteappcleanarchitecture.R
import com.yasserakbbach.noteappcleanarchitecture.databinding.FragmentListNotesBinding
import com.yasserakbbach.noteappcleanarchitecture.framework.ListNotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListNotesFragment : Fragment() {

    private val viewModel : ListNotesViewModel by viewModels()
    private var _binding : FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    private val notesAdapter : ListNotesAdapter by lazy {
        ListNotesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.addNote).setOnClickListener {
            goToNoteDetails()
        }

        setupNotesListRV()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadNotes().invokeOnCompletion {
            toggleLoader(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToNoteDetails(id : Long = 0L) {

        val action = ListNotesFragmentDirections.actionListNotesFragmentToNoteFragment(id)
        findNavController().navigate(action)
    }

    private fun setupNotesListRV() {

        binding.rvNotes.adapter = notesAdapter
        observeNotes()
    }

    private fun observeNotes() = lifecycleScope.launchWhenCreated {

        viewModel.notes.collectLatest {
            notesAdapter.submitList(it.sortedByDescending { note ->  note.id })
        }
    }

    private fun toggleLoader(hide : Boolean) = lifecycleScope.launch(Dispatchers.Main) {
        binding.loading.visibility = if(hide) View.GONE else View.VISIBLE
    }
}