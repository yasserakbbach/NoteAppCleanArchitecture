package com.yasserakbbach.noteappcleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.core.repository.NoteRepository
import com.yasserakbbach.core.usecase.AddNote
import com.yasserakbbach.core.usecase.GetAllNotes
import com.yasserakbbach.core.usecase.GetNote
import com.yasserakbbach.core.usecase.RemoveNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ListNotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository(RoomNoteDataSource(application))
    private val useCases = UseCases(
        addNote = AddNote(repository),
        getAllNotes = GetAllNotes(repository),
        getNote = GetNote(repository),
        removeNote = RemoveNote(repository)
    )
    val notes = MutableSharedFlow<List<Note>>()

    fun loadNotes() = viewModelScope.launch(Dispatchers.IO) {

        notes.emit(useCases.getAllNotes())
    }
}