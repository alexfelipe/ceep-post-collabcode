package br.com.alexf.ceep.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.alexf.ceep.R
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.CallbackResponse
import br.com.alexf.ceep.retrofit.client.NoteWebClient
import br.com.alexf.ceep.ui.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.form_note.view.*

class NoteListActivity : AppCompatActivity() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        NoteWebClient().list(object : CallbackResponse<List<Note>> {
            override fun success(notes: List<Note>) {
                this@NoteListActivity.notes.addAll(notes)
                configureList()
            }
        })

        fab_add_note.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val createdView = LayoutInflater.from(this@NoteListActivity)
                        .inflate(R.layout.form_note,
                                window.decorView as ViewGroup,
                                false)

                AlertDialog.Builder(this@NoteListActivity)
                        .setTitle("Add note")
                        .setView(createdView)
                        .setPositiveButton("Save", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val title = createdView.form_note_title.text.toString()
                                val description = createdView.form_note_description.text.toString()
                                val note = Note(title, description)
                                NoteWebClient().insert(note, object : CallbackResponse<Note> {
                                    override fun success(note: Note) {
                                        this@NoteListActivity.notes.add(note)
                                        configureList()
                                    }
                                })
                            }
                        })
                        .show()
            }
        })

    }

    private fun configureList() {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}
