package com.test.myapplication.util

import com.test.myapplication.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> createSafeCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> Response<T>): Resource<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(
                    "Response is fail, code is ${response.code()}, error body is ${response.errorBody()}"
                    , null
                )
            }
        } catch (throwable: Throwable) {
            Resource.error(throwable.message, null)
        }
    }
}