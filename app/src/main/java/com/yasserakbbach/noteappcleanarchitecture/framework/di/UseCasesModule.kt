package com.yasserakbbach.noteappcleanarchitecture.framework.di

import com.yasserakbbach.core.repository.NoteRepository
import com.yasserakbbach.core.usecase.AddNote
import com.yasserakbbach.core.usecase.GetAllNotes
import com.yasserakbbach.core.usecase.GetNote
import com.yasserakbbach.core.usecase.RemoveNote
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
        RemoveNote(repository)
    )
}