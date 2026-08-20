package com.example.rkmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rkmart.ui.theme.RKMartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RKMartTheme {
                    HomeScreen()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    // remember is remembering the state object containing count.
    var count by remember {
        mutableStateOf(0)
    }
    CartCounter(
        count = count,
        // "CartCounter, here's an action you can invoke later."
        // is simply a lambda being passed down to CartCounter.
        onAddItem = {
            count++
        }
    )
}

@Composable
fun CartCounter(count: Int, onAddItem: () -> Unit) {
    Column {
        Text("Cart Item: $count")
        Button(
            onClick = onAddItem
        ) {
            Text("Add Item")
        }
    }
}