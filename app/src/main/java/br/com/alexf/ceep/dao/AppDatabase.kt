package br.com.alexf.ceep.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.alexf.ceep.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}