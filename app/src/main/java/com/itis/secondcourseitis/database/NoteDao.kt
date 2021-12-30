package com.itis.secondcourseitis.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itis.secondcourseitis.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteNoteById(id: Int)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findNoteById(id: Int): Note

    @Query("SELECT * FROM notes")
    fun findAllNotes(): List<Note>
}
