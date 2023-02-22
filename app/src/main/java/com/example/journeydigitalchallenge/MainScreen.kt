package com.example.journeydigitalchallenge

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController,
) {
    val posts by viewModel.posts.observeAsState(emptyList())

    // State variable to hold the user's search query
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {

        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                cursorColor = MaterialTheme.colors.primaryVariant
            ))

        // Post list
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)) {
            // Show all posts when search bar is empty
            val filteredPosts = if (searchQuery.text.isEmpty()) {
                posts
            } else {
                posts.filter { post ->
                    post.title.contains(searchQuery.text, true) || post.body.contains(searchQuery.text, true)
                }
            }

            items(filteredPosts) { post ->
                PostListItem(post) {
                    viewModel.showPostDetails(post.id, navController)
                }
            }
        }
    }
}


@Composable
fun PostListItem(post: Post, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .height(100.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(2.dp)
            .clickable(onClick = onClick)
    ) {

        Column()
        {
            // Title
            Text(
                text = post.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )

            // Body
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(horizontal = 14.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}




