package com.test.myapplication.repository

import com.test.myapplication.Resource
import com.test.myapplication.model.Post
import com.test.myapplication.network.ApiClient
import com.test.myapplication.network.ClientApi
import retrofit2.Response
import com.test.myapplication.util.createSafeCall
import kotlinx.coroutines.Dispatchers

object DataRepository {
    private var apiInterface: ClientApi = ApiClient.getClient().create(
        ClientApi::class.java
    )

    suspend fun getPosts() : Resource<List<Post>>{
        return createSafeCall(Dispatchers.IO) {apiInterface.getPosts()}
    }

}