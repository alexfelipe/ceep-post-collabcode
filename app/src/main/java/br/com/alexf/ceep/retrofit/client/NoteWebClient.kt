package br.com.alexf.ceep.retrofit.client

import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.RetrofitInitializer
import br.com.alexf.ceep.retrofit.callback
import br.com.alexf.ceep.retrofit.defaultFailure
import br.com.alexf.ceep.retrofit.defaultResponse

class NoteWebClient {

    fun list(preExecute: () -> Unit = {},
             finished: () -> Unit = {},
             success: (notes: List<Note>) -> Unit = {},
             failure: (throwable: Throwable) -> Unit = {}) {
        val call = RetrofitInitializer().noteService().list()
        call.enqueue(
                callback(
                        response = { it.defaultResponse(success) },
                        failure = { it.defaultFailure(failure) },
                        preExecute = preExecute,
                        finished = finished))
    }

    fun insert(note: Note,
               preExecute: () -> Unit = {},
               finished: () -> Unit = {},
               success: (note: Note) -> Unit = {},
               failure: (throwable: Throwable) -> Unit = {}) {

        val call = RetrofitInitializer().noteService().insert(note)
        call.enqueue(
                callback(
                        response = { it.defaultResponse(success) },
                        failure = { it.defaultFailure(failure) },
                        preExecute = preExecute,
                        finished = finished))
    }

    fun alter(note: Note,
              preExecute: () -> Unit = {},
              finished: () -> Unit = {},
              success: (note: Note) -> Unit = {},
              failure: (throwable: Throwable) -> Unit = {}) {
        val call = RetrofitInitializer()
                .noteService().alter(note, note.id)
        call.enqueue(
                callback(
                        response = { it.defaultResponse(success) },
                        failure = { it.defaultFailure(failure) },
                        preExecute = preExecute,
                        finished = finished))
    }

}