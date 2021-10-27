package com.yasserakbbach.noteappcleanarchitecture.framework.di

import com.yasserakbbach.noteappcleanarchitecture.framework.ListNotesViewModel
import com.yasserakbbach.noteappcleanarchitecture.framework.NoteViewModel
import dagger.Component

@Component(
    modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class]
)
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listNotesViewModel: ListNotesViewModel)
}