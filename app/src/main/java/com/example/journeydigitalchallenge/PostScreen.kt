package com.example.journeydigitalchallenge

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun PostScreen(
    post: Post,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {

        // Post
        Box(modifier = Modifier
            .weight(1f)
            .background(Color.Cyan)) {

            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)

            ) {

                // Post title
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold
                )
                // Post body
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = post.body,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 10.dp))

            }
        }


        // Comments
        Box(modifier = Modifier
            .weight(1f)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {

            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Top) {
                Text(
                    text = "Comments",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                CommentList(post.id)
            }

        }
    }
}

@Composable
fun CommentList(postId: Int) {
    var comments by remember { mutableStateOf(emptyList<Comment>()) }

    LaunchedEffect(postId) {
        comments = apiService.getCommentsForPost(postId)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(comments) { comment ->
            CommentListItem(comment)
        }
    }
}

@Composable
fun CommentListItem(comment: Comment) {
    Spacer(modifier = Modifier.height(12.dp))

    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = comment.name, style = MaterialTheme.typography.h6)
        Text(text = comment.email,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = comment.body,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 6.dp))
    }
}
