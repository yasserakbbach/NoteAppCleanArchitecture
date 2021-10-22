package com.yasserakbbach.core.usecase

import com.yasserakbbach.core.data.Note
import com.yasserakbbach.core.repository.NoteRepository

class RemoveNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note : Note) = noteRepository.removeNote(note)
}