package br.com.alexf.ceep.ui.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import br.com.alexf.ceep.R
import br.com.alexf.ceep.dao.AppDatabase
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.client.NoteWebClient
import br.com.alexf.ceep.ui.adapter.NoteListAdapter
import br.com.alexf.ceep.ui.dialog.AddNoteDialog
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.coroutines.experimental.async


class NoteListActivity : AppCompatActivity() {

    private var notes: MutableList<Note> = mutableListOf()
    private val adapter = NoteListAdapter(notes, this)
    private val webClient = NoteWebClient()
    private val db by lazy {
        Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "ceep").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        configureComponents()
        loadOnlineNotes()
    }

    private fun configureComponents() {
        configureList()
        configureFab()
    }

    private fun configureFab() {
        fab_add_note.setOnClickListener {
            showFormDialog()
        }
    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = adapter
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun showFormDialog() {
        AddNoteDialog(window.decorView as ViewGroup, this)
                .show {
                    webClient.insert(it, { savedNote ->
                        notes.add(savedNote)
                    }, {
                        Toast.makeText(this, "Falha ao inserir uma nota", Toast.LENGTH_LONG).show()
                    })
                }
    }

    private fun loadOnlineNotes() {
        webClient.list(success = { receivedNote ->
            notes.addAll(receivedNote)
            notifyRecyclerView()
        }, failure = { throwable ->
            Toast.makeText(this, "Falha ao buscar as notas", Toast.LENGTH_LONG).show()
        })
    }

    private fun notifyRecyclerView() {
        adapter.notifyDataSetChanged()
    }

}
