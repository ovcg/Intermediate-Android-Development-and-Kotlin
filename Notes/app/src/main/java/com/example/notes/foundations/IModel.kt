package com.example.notes.foundations

typealias SuccessCallBack = (Boolean) -> Unit

interface IModel<T> {
    fun add(model: T, callback: SuccessCallBack)
    fun update(model: T, callback: SuccessCallBack)
    fun delete(model: T, callback: SuccessCallBack)
    fun retrieve(): List<T>
}