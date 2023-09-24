package com.daggerhilt.postsapp.network

import com.daggerhilt.postsapp.models.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("posts")
    suspend fun getPosts():List<Post>

    @POST("posts")
    suspend fun addPost(
        @Body post: Post
    ): Post
}