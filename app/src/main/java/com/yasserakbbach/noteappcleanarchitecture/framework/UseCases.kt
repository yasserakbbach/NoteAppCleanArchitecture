package com.yasserakbbach.noteappcleanarchitecture.framework

import com.yasserakbbach.core.usecase.AddNote
import com.yasserakbbach.core.usecase.GetAllNotes
import com.yasserakbbach.core.usecase.GetNote
import com.yasserakbbach.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)