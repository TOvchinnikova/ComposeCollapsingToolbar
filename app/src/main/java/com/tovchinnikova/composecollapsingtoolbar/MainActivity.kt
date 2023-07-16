package com.tovchinnikova.composecollapsingtoolbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tovchinnikova.composecollapsingtoolbar.ui.theme.ComposeCollapsingToolbarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animals = listOf(
            "American Wirehair",
            "Antelope",
            "Barn Swallow",
            "Coastal Taipan",
            "Eastern Coral Snake",
            "Eastern Cottontail",
            "Eastern Diamondback Rattlesnake",
            "Eastern Dobsonfly",
            "Eastern Fence Lizard",
            "Eastern Glass Lizard",
            "Eastern Gorilla",
            "Eastern Gray Squirrel",
            "Eastern Green Mamba",
            "Eastern Hognose Snake",
            "Eastern Indigo Snake",
            "Eastern Kingbird",
            "Eastern Lowland Gorilla",
            "Cockapoo",
            "Jaguar",
            "Scarlet Macaw",
            "Baby Green Sea Turtle",
            "Red-Eyed Tree Frog",
            "Hoffman's Two-Toed Sloth",
            "Resplendent Quetzal",
            "White-Nosed Coati",
            "Green Iguana",
            "Keel-Billed Toucan",
            "Capuchin Monkey",
            "Emerald Basilisk",
            "King Vulture",
        )

        setContent {
            ComposeCollapsingToolbarTheme {
                val lazyScrollState = rememberLazyListState()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        MotionAppBar(lazyScrollState)
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .animateContentSize()
                            .padding(paddingValues = it),
                        state = lazyScrollState
                    ) {
                        itemsIndexed(animals) { index, item ->
                            Item(text = item)

                            if (index != animals.lastIndex)
                                Divider(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Item(
    text: String
) {
    Text(modifier = Modifier.padding(16.dp), text = text)
}