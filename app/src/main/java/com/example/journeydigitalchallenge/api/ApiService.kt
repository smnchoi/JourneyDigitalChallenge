package com.example.journeydigitalchallenge.api

import com.example.journeydigitalchallenge.model.data.Comment
import com.example.journeydigitalchallenge.model.data.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}/comments")
    suspend fun getCommentsForPost(@Path("id") postId: Int): List<Comment>
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)
