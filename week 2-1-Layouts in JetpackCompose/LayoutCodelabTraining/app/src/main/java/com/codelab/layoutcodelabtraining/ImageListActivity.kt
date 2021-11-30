package com.codelab.layoutcodelabtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.codelab.layoutcodelabtraining.ui.theme.LayoutCobelabTrainingTheme
import kotlinx.coroutines.launch

class ImageListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCobelabTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ScrollingList()
                }
            }
        }
    }
}

/*@Composable
fun SimpleList() {
    Column {
        repeat(100) {
            Text("Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}*/

@Composable
fun SimpleList() {
    // save the scrolling position with this state
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text("Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun LazyList() {
    // Save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Text("Item #$it", style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList() {
    // Save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            ImageListItem(it)
        }
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Preview
@Composable
fun SimpleListPreview() {
    SimpleList()
}

@Preview
@Composable
fun LazyListPreview() {
    LazyList()
}

@Preview
@Composable
fun ImageListPreview() {
    ImageList()
}

@Preview
@Composable
fun ScrollingListPreview() {
    LayoutCobelabTrainingTheme {
        ScrollingList()
    }
}