package com.daggerhilt.postsapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.daggerhilt.postsapp.models.Post
import com.daggerhilt.postsapp.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application, private val mainRepository: MainRepository) :
    AndroidViewModel(application) {
    private val postResponse:MutableLiveData<Post> = MutableLiveData()
    val post:LiveData<Post> = postResponse

    fun addPost(title: String, body: String) {
        viewModelScope.launch {
            mainRepository.addPost(title, body)
                .catch { e -> Log.e("MainViewModel", "addPost: ${e.message}") }
                .collect { post -> postResponse.postValue(post) }
        }
    }

    val response: LiveData<List<Post>> = mainRepository.getPosts()
        .catch { e -> Log.e("MainViewModel", "getPosts: ${e.message}") }
        .asLiveData()
}