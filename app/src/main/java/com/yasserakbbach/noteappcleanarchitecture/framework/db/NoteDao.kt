package com.yasserakbbach.noteappcleanarchitecture.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNote(id : Long) : NoteEntity?

    @Query("SELECT * FROM note")
    suspend fun getAllNotes() : List<NoteEntity>

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}