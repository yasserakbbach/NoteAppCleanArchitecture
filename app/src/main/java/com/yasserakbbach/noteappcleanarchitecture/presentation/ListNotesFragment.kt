package com.yasserakbbach.noteappcleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yasserakbbach.noteappcleanarchitecture.R

class ListNotesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.addNote).setOnClickListener {
            goToNoteDetails()
        }
    }

    private fun goToNoteDetails(id : Long = 0L) {

        val action = ListNotesFragmentDirections.actionListNotesFragmentToNoteFragment(id)
        findNavController().navigate(action)
    }
}