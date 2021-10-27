package com.yasserakbbach.noteappcleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.noteappcleanarchitecture.framework.di.ApplicationModule
import com.yasserakbbach.noteappcleanarchitecture.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListNotesViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var useCases : UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }

    val notes = MutableSharedFlow<List<Note>>()

    fun loadNotes() = viewModelScope.launch(Dispatchers.IO) {

        notes.emit(useCases.getAllNotes())
    }
}