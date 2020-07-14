package com.test.myapplication.network

import com.test.myapplication.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ClientApi {

    @GET("posts")
    suspend fun getPosts() : Response<List<Post>>
}