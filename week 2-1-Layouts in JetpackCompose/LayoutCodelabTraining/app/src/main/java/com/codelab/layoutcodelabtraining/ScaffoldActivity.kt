package com.codelab.layoutcodelabtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codelab.layoutcodelabtraining.ui.theme.LayoutCobelabTrainingTheme

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCobelabTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ScaffoldLayoutsCodelab()
                }
            }
        }
    }
}

/*@Composable
fun LayoutsCodelab() {
    Text(text = "Hi there!")
}*/

/**
 * using scaffold
 */
/*@Composable
fun LayoutsCodelab() {
    Scaffold { innerPadding ->
        Text(text = "Hi there!", modifier = Modifier.padding(innerPadding))
    }
}*/

/**
 * using scaffold with column
 */
/*@Composable
fun LayoutsCodelab() {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Hi there!")
            Text(text = "Thanks for going through the Layouts codelab")
        }
    }
}*/


/**
 * using scaffold and more reusable and testable
 */
/*@Composable
fun LayoutsCodelab() {
    Scaffold { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}
*/

@Composable
fun ScaffoldLayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        ScaffoldBodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScaffoldBodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview
@Composable
fun ScaffoldLayoutsCodelabPreview() {
    LayoutCobelabTrainingTheme {
        ScaffoldLayoutsCodelab()
    }
}