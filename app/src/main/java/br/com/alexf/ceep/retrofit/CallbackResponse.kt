package br.com.alexf.ceep.retrofit

interface CallbackResponse<T> {
    fun success(response: T)
}