package com.yasserakbbach.noteappcleanarchitecture.framework.di

import android.app.Application
import com.yasserakbbach.core.repository.NoteRepository
import com.yasserakbbach.noteappcleanarchitecture.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}