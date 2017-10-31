package br.com.alexf.ceep.retrofit.client

import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.RetrofitInitializer
import br.com.alexf.ceep.retrofit.callback

class NoteWebClient {

    fun list(success: (notes: List<Note>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().list()
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun insert(note: Note, success: (note: Note) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().noteService().insert(note)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

}