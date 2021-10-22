package com.yasserakbbach.core.usecase

import com.yasserakbbach.core.repository.NoteRepository

class GetAllNotes(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke() = noteRepository.getAllNotes()
}