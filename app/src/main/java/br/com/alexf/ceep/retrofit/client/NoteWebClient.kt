package br.com.alexf.ceep.retrofit.client

import br.com.alexf.ceep.model.Note
import br.com.alexf.ceep.retrofit.RetrofitInitializer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteWebClient {

    fun list(success: (notes: List<Note>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {
        val flowable = RetrofitInitializer().noteService.list()
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, failure)
    }

    fun insert(note: Note, success: (note: Note) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val single = RetrofitInitializer().noteService.insert(note)
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, failure)

    }

}