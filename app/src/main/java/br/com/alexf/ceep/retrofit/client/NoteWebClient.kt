package br.com.alexf.ceep.retrofit.client

import android.util.Log
import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.CallbackResponse
import br.com.alexf.ceep.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteWebClient {

    fun list(callbackResponse: CallbackResponse<List<Note>>) {
        val call = RetrofitInitializer().noteService().list()
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    callbackResponse.success(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    fun insert(note: Note, callbackResponse: CallbackResponse<Note>) {
        val call = RetrofitInitializer().noteService().insert(note)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>?, response: Response<Note?>?) {
                response?.body()?.let {
                    val note: Note = it
                    callbackResponse.success(note)
                }
            }

            override fun onFailure(call: Call<Note?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

}