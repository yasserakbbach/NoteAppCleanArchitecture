package com.yasserakbbach.noteappcleanarchitecture.framework.di

import com.yasserakbbach.core.repository.NoteRepository
import com.yasserakbbach.core.usecase.*
import com.yasserakbbach.noteappcleanarchitecture.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}