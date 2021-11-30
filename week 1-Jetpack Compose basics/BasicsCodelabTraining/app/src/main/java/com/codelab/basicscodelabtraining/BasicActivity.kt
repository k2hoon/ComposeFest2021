package com.codelab.basicscodelabtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basicscodelabtraining.ui.theme.BasicsCodelabTrainingTheme

class BasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTrainingTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/
                GreetingApp()
            }
        }
    }
}

/*@Composable
private fun Greeting(name: String) {
    Text(text = "Hello $name!")
}*/

@Composable
private fun GreetingApp() {
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }
}


@Composable
private fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {
//        Text(text = "Hello $name!")
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}


/**
 * The composable method name must be unique regardless of the accessor.
 */
@Preview(showBackground = true, name = "Text preview")
@Composable
fun GreetingPreview() {
    BasicsCodelabTrainingTheme {
        //Greeting(name = "Android")
        GreetingApp()
    }
}
