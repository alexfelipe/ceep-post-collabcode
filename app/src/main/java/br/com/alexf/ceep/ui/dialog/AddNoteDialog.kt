package br.com.alexf.ceep.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import br.com.alexf.ceep.R
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.client.NoteWebClient
import kotlinx.android.synthetic.main.form_note.view.*

class AddNoteDialog(private val viewGroup: ViewGroup,
                    private val context: Context) {

    fun show(created: (createdNote: Note) -> Unit) {
        val createdView = LayoutInflater.from(context)
                .inflate(R.layout.form_note,
                        viewGroup,
                        false)


        AlertDialog.Builder(context)
                .setTitle("Add note")
                .setView(createdView)
                .setPositiveButton("Save") { _, _ ->
                    val title = createdView.form_note_title.text.toString()
                    val description = createdView.form_note_description.text.toString()
                    val note = Note(title = title, description = description)
                    NoteWebClient().insert(note, {
                        created(it)
                    }, {
                        Toast.makeText(context, "Falha ao salvar nota", Toast.LENGTH_LONG).show()
                    })
                }
                .show()
    }

}