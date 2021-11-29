package com.example.compose.rally

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var currentScreen: MutableState<RallyScreen>


    // TODO: Add tests
    @Test
    fun myTest() {
        composeTestRule.setContent {
            Text("You can set any Compose content!")
        }
    }

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        Thread.sleep(5000)
    }

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        // It fails...
        /*composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()*/

        // print the Semantics tree
        /*composeTestRule.onRoot().printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithText(RallyScreen.Accounts.name.uppercase(Locale.getDefault()))
            .assertExists() // Still fails*/

        // Replace the finder onNodeWithText with onNodeWithContentDescription
        /*composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertExists()*/

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_selectAccountTab() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            currentScreen = rememberSaveable { mutableStateOf(RallyScreen.Overview) }
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen.value = screen },
                    currentScreen = currentScreen.value
                )
            }
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        val contentDescription = RallyScreen.Accounts.name
        composeTestRule
            .onNodeWithContentDescription(contentDescription)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(contentDescription)
            .assertIsSelected()

        assertEquals(currentScreen.value.name, contentDescription)
    }

    @Test
    fun rallyTopAppBarTest_selectBillsTab() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            currentScreen = rememberSaveable { mutableStateOf(RallyScreen.Overview) }
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen.value = screen },
                    currentScreen = currentScreen.value
                )
            }
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        val contentDescription = RallyScreen.Bills.name
        composeTestRule
            .onNodeWithContentDescription(contentDescription)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(contentDescription)
            .assertIsSelected()

        assertEquals(currentScreen.value.name, contentDescription)
    }

    /*@Test
    fun rallyTopAppBarTest_printToLog() {
        composeTestRule.setContent {
            val allScreens = RallyScreen.values().toList()
            currentScreen = rememberSaveable { mutableStateOf(RallyScreen.Overview) }
            Scaffold(
                topBar = {
                    RallyTopAppBar(
                        allScreens = allScreens,
                        onTabSelected = { screen -> currentScreen.value = screen },
                        currentScreen = currentScreen.value
                    )
                }
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    currentScreen.value.content(onScreenChange = { screen -> currentScreen.value = screen })
                }
            }
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
    }*/
}