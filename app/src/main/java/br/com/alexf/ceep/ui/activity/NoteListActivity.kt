package br.com.alexf.ceep.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.alexf.ceep.R
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        note_list_textview.text = "Bem vindo ao Ceep"
    }

}
