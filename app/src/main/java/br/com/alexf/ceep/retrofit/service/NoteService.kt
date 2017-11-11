package br.com.alexf.ceep.retrofit.service

import br.com.alexf.ceep.model.Note
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteService {
    @GET("notes")
    fun list(): Flowable<List<Note>>

    @POST("notes")
    fun insert(@Body note: Note): Single<Note>
}