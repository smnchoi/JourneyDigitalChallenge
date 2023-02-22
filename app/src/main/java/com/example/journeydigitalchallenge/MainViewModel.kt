package com.example.journeydigitalchallenge

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    val searchQuery = mutableStateOf("")

    init {
        viewModelScope.launch {
            val fetchedPosts = apiService.getPosts()
//            Log.i("============= Title: ", fetchedPosts[0].title)
            _posts.value = fetchedPosts
        }
    }

    fun showPostDetails(post: Post) {
        // TODO: Navigate to PostScreen with post data
    }
}
