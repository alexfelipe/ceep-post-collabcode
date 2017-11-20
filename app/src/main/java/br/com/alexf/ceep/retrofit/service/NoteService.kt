package br.com.alexf.ceep.retrofit.service

import br.com.alexf.ceep.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NoteService {
    @GET("notes")
    fun list(): Call<List<Note>>

    @POST("notes")
    fun insert(@Body note: Note): Call<Note>

    @PUT("notes/{id}")
    fun alter(@Body note: Note, @Path("id") id: Int): Call<Note>
}