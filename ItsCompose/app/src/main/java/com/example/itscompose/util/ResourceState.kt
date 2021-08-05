package com.example.itscompose.util

sealed class ResourceState<T>(val data:T? = null, val message: String? = null) {
    class SUCCESS<T>(data: T): ResourceState<T>(data)
    class ERROR<T>(message:String, data: T? = null): ResourceState<T>(data, message)
    class LOADING<T>(data: T? = null): ResourceState<T>(data)
}