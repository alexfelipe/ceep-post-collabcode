package br.com.alexf.ceep.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import br.com.alexf.ceep.R
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.RetrofitInitializer
import br.com.alexf.ceep.ui.adapter.NoteListAdapter
import kotlinx.android.synthetic.main.activity_note_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val call = RetrofitInitializer().noteService().list()
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun configureList(notes: List<Note>) {
        val recyclerView = note_list_recyclerview
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}
