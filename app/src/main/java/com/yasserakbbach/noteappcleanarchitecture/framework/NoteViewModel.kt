package com.yasserakbbach.noteappcleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.core.repository.NoteRepository
import com.yasserakbbach.core.usecase.AddNote
import com.yasserakbbach.core.usecase.GetAllNotes
import com.yasserakbbach.core.usecase.GetNote
import com.yasserakbbach.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = NoteRepository(RoomNoteDataSource(application))
    private val useCases = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )
    val saved = MutableSharedFlow<Boolean>()
    val currentNote = MutableSharedFlow<Note?>()

    fun saveNote(note : Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.emit(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            useCases.getNote(id)?.let {
                currentNote.emit(it)
            }
        }
    }
}