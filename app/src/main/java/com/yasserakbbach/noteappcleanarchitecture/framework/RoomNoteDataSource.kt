package com.yasserakbbach.noteappcleanarchitecture.framework

import android.content.Context
import com.yasserakbbach.core.data.Note
import com.yasserakbbach.core.repository.NoteDataSource
import com.yasserakbbach.noteappcleanarchitecture.framework.db.DatabaseService
import com.yasserakbbach.noteappcleanarchitecture.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDataSource{

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.insertNote(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNote(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNotes().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNote(NoteEntity.fromNote(note))
}