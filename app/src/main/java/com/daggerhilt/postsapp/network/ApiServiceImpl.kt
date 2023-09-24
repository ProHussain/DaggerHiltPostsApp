package com.daggerhilt.postsapp.network

import com.daggerhilt.postsapp.models.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getPosts() = apiService.getPosts()

    suspend fun addPost(title: String, body: String) = apiService.addPost(Post(body,0,title,1))
}