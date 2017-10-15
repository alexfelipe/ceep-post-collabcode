package br.com.alexf.ceep.retrofit.service

import br.com.alexf.ceep.model.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteService {
    @GET("notes")
    fun list(): Call<List<Note>>

    @POST("notes")
    fun insert(@Body note: Note): Call<Note>
}