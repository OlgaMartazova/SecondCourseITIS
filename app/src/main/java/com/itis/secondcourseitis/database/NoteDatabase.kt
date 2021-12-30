package com.itis.secondcourseitis.database

import android.content.Context
import androidx.room.*
import com.itis.secondcourseitis.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private lateinit var instance: NoteDatabase
        private var hasInstance = false

        fun getInstance(context: Context): NoteDatabase {
            if (!hasInstance) {
                instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                hasInstance = true
            }
            return instance
        }
    }
}
