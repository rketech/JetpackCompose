package com.example.rkmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.rows
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.example.rkmart.ui.theme.RKMartTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment

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
    // state hoisting -- One state. Multiple UI components.
    var count by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RKMartHeader(count)

        Spacer(modifier = Modifier.height(40.dp))

        CartCounter(
            count = count,
            // "CartCounter, here's an action you can invoke later."
            // is simply a lambda being passed down to CartCounter.
            onAddItem = {
                count++
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        ProductCard(
            onAddItem = {
                count++
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        CartSummary(
            count = count,
        )
    }
}

@Composable
fun CartCounter(count: Int, onAddItem: () -> Unit) {
    Column {
        Text("Cart Counter")
        Text("Cart Item: $count")
        Button(
            onClick = onAddItem
        ) {
            Text("Add Item")
        }
    }
}

@Composable
fun ProductCard(onAddItem: () -> Unit) {
    Column {
        Text("Product Card")
        Button(
            onClick = onAddItem
        ) {
            Text("Add Item")
        }
    }
}

@Composable
fun CartSummary(count: Int) {
    Column {
        Text("Cart Summary")
        Text("Items in Cart: $count")
    }
}

@Composable
fun RKMartHeader(count: Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("RKMart")
        Text("Cart : $count")
    }
}