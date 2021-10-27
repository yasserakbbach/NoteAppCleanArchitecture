package com.yasserakbbach.noteappcleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.noteappcleanarchitecture.framework.di.ApplicationModule
import com.yasserakbbach.noteappcleanarchitecture.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases : UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }

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

    fun deleteNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
        }
    }
}