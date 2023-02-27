package com.example.journeydigitalchallenge.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.journeydigitalchallenge.model.data.Post
import com.example.journeydigitalchallenge.api.apiService
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    init {
        viewModelScope.launch {
            val fetchedPosts = apiService.getPosts()
            _posts.value = fetchedPosts
        }
    }

    fun showPostDetails(postId: Int, navController: NavController) {
        navController.navigate("postScreen/$postId")
    }

    fun getPostById(postId: Int): Post? {
        return _posts.value?.find { it.id == postId }
    }
}
