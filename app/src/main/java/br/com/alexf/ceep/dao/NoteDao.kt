package br.com.alexf.ceep.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.alexf.ceep.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

    @Insert
    fun insertAll(notes: List<Note>)

    @Query("DELETE FROM note")
    fun removeAll(): Unit
}