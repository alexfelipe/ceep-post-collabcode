package br.com.alexf.ceep.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var title: String = "",
        var description: String = "")