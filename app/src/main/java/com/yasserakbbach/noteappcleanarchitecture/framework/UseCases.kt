package com.yasserakbbach.noteappcleanarchitecture.framework

import com.yasserakbbach.core.usecase.*

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val wordCount: GetWordCount
)