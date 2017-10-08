package br.com.alexf.ceep.retrofit.service

import br.com.alexf.ceep.model.Note
import retrofit2.Call
import retrofit2.http.GET

interface NoteService {
    @GET("notes")
    fun list(): Call<List<Note>>
}