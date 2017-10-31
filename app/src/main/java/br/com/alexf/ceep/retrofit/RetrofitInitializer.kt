package br.com.alexf.ceep.retrofit

import br.com.alexf.ceep.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.108:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun noteService() = retrofit.create(NoteService::class.java)
}