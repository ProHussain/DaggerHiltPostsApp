package com.daggerhilt.postsapp.repo

import com.daggerhilt.postsapp.models.Post
import com.daggerhilt.postsapp.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getPosts():Flow<List<Post>> = flow {
        emit(apiServiceImpl.getPosts())
    }.flowOn(Dispatchers.IO)

    fun addPost(title: String, body: String): Flow<Post> = flow {
        emit(apiServiceImpl.addPost(title, body))
    }.flowOn(Dispatchers.IO)
}