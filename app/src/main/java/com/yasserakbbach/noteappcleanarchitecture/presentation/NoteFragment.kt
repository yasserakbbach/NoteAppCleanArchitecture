package com.yasserakbbach.noteappcleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.noteappcleanarchitecture.databinding.FragmentNoteBinding
import com.yasserakbbach.noteappcleanarchitecture.framework.NoteViewModel
import kotlinx.coroutines.flow.collectLatest

class NoteFragment : Fragment() {

    private val viewModel : NoteViewModel by viewModels()
    private var mNote = Note(0L, "", "", 0L, 0L)

    private var _binding : FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveOrAddNote.setOnClickListener {
            saveOrAddNote()
        }
        observeSavedNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveOrAddNote() {

        if(isValidNote()) {

            val time = System.currentTimeMillis()
            mNote.apply {
                title = binding.title.text.toString()
                content = binding.content.text.toString()
                updateTime = time

                if(id == 0L) {
                    creationTime = time
                }
            }.also {
                viewModel.saveNote(it)
            }

        }else {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun isValidNote() =
        binding.title.text?.isNotEmpty() == true
                && binding.content.text?.isNotEmpty() == true

    private fun observeSavedNote() = lifecycleScope.launchWhenCreated {

        viewModel.saved.collectLatest {

            if(it) {

                Toast.makeText(requireContext(), "Note saved successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp() // Navigate back to notes list
            }else {

                Toast.makeText(requireContext(), "Error occurred!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}