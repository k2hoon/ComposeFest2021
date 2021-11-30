package com.codelab.layoutcodelabtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codelab.layoutcodelabtraining.ui.theme.LayoutCobelabTrainingTheme

class CustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCobelabTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CustomLayoutsCodelab()
                }
            }
        }
    }
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Composable
fun CustomLayoutsCodelab() {
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
        CustomLayoutBodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun CustomLayoutBodyContent(modifier: Modifier = Modifier) {
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    LayoutCobelabTrainingTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    LayoutCobelabTrainingTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}

@Preview
@Composable
fun MyOwnColumnPreview() {
    LayoutCobelabTrainingTheme {
        Surface(Modifier.padding(8.dp)) {
            MyOwnColumn {
                Text("MyOwnColumn")
                Text("places items")
                Text("vertically.")
                Text("We've done it by hand!")
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutsCodelabPreview() {
    LayoutCobelabTrainingTheme {
        CustomLayoutsCodelab()
    }
}

