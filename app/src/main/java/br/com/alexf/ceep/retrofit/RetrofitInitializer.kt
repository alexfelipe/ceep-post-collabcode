package br.com.alexf.ceep.retrofit

import br.com.alexf.ceep.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val noteService get() = retrofit.create(NoteService::class.java)

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.34:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}