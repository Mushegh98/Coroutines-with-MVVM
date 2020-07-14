package com.test.myapplication.network

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.test.myapplication.Resource

abstract class NetworkBoundResource<T>
@MainThread constructor(){
    private val result = MutableLiveData<Resource<T?>>()

    init {
        result.postValue(Resource.loading(null))
        @Suppress("LeakingThis")
        createCall()
    }

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall()

    fun setValue(newValue: Resource<T?>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    fun asMutableLiveData() : MutableLiveData<Resource<T?>> {
        return result
    }

}