package com.example.journeydigitalchallenge

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

val _post = Post(
    1,
    1,
    "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
)

val _posts = listOf(
    Post(
        1,
        1,
        "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    ),
    Post(
        2,
        1,
        "qui est esse",
        "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
    ),
    Post(
        3,
        1,
        "ea molestias quasi exercitationem repellat qui ipsa sit aut",
        "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"
    ),
    Post(
        4,
        1,
        "eum et est occaecati",
        "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit"
    ),
    Post(
        5,
        1,
        "nesciunt quas odio",
        "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
    ),
    Post(
        5,
        1,
        "nesciunt quas odio",
        "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
    ),
    Post(
        5,
        1,
        "nesciunt quas odio",
        "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
    ),
    Post(
        5,
        1,
        "nesciunt quas odio",
        "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
    ),
)

val _comments = listOf(
    Comment(
        1,
        1,
        "labore",
        "Eliseo@gardner.biz",
        "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
    ),
    Comment(
        2,
        1,
        "quo",
        "Jayne_Kuhic@sydney.com",
        "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et"
    ),
    Comment(
        3,
        1,
        "odio adipisci",
        "Nikita@garfield.biz",
        "quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione"
    ),
    Comment(
        4,
        1,
        "alias odio sit",
        "Lew@alysha.tv",
        "non et atque\noccaecati deserunt quas accusantium unde odit nobis\nqui volupt"
    )
)

@Composable
fun PostScreen() {
    Column() {
//        PostDetail(post = _post, comments = _comments)
        PostList(posts = _posts,
            onItemClick = { postId -> postId }
        )
    }
}

@Composable
fun PostDetail(
    post: Post,
    comments: List<Comment>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color.Cyan)
    ) {
        // Title
        Text(text = post.title, style = MaterialTheme.typography.h4)

        // Body
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = post.body, style = MaterialTheme.typography.body1)

        // Comments
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Comments", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.Blue)
        ) {
            items(comments) { comment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        Column(modifier = Modifier.padding(6.dp)) {
                            Text(text = comment.name, style = MaterialTheme.typography.subtitle1)
                            Text(text = comment.email, style = MaterialTheme.typography.caption)
                        }
                        Text(
                            text = comment.body,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

// TODO: using PostList
@Composable
fun PostList(
    posts: List<Post>,
    onItemClick: (postId: Int) -> Unit
) {
    LazyColumn {
        items(posts) { post ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(post.id) }
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .background(color = Color.DarkGray)
                )
                {
                    // Title
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    // Body
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}