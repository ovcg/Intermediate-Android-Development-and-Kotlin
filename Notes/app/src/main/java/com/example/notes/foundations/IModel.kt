package com.example.notes.foundations

typealias SuccessCallback = (Boolean) -> Unit

interface IModel<T> {
    suspend fun add(model: T, callback: SuccessCallback)
    suspend fun update(model: T, callback: SuccessCallback)
    suspend fun delete(model: T, callback: SuccessCallback)
    fun retrieve(callback: (List<T>?) -> Unit)
}