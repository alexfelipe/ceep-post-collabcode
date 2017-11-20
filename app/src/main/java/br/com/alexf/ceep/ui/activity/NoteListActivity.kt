package br.com.alexf.ceep.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import android.widget.Toast
import br.com.alexf.ceep.R
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.client.NoteWebClient
import br.com.alexf.ceep.ui.adapter.NoteListAdapter
import br.com.alexf.ceep.ui.dialog.NoteDialog
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list({
            notes.addAll(it)
            configureList()
        }, {
            Toast.makeText(this, "Falha ao buscar as notas", Toast.LENGTH_LONG).show()
        })

        fab_add_note.setOnClickListener {
            NoteDialog(window.decorView as ViewGroup, this)
                    .add {
                        notes.add(it)
                        configureList()
                    }
        }
    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this) { note, position ->
            NoteDialog(window.decorView as ViewGroup, this).alter(note) {
                notes[position] = it
                configureList()
            }
        }
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}
