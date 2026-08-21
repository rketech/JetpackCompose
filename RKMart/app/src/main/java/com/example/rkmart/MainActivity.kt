package com.example.rkmart

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.rows
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color

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

data class Product(
    val name: String,
    val price: Int
)

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    // remember is remembering the state object containing count.
    // state hoisting -- One state. Multiple UI components.
    var count by remember {
        mutableStateOf(0)
    }

    // Temporary Database
    val products = listOf(
        Product(
            name = "Samsung Galaxy Ultra",
            price = 45000
        ),

        Product(
            name = "One Plus N6",
            price = 24999
        ),

        Product(
            name = "Lava Bold N2",
            price = 9699
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            RKMartHeader(count)
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text("One")
                Text("Two")
                Text("Three")
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            CartCounter(
                count = count,
                // "CartCounter, here's an action you can invoke later."
                // is simply a lambda being passed down to CartCounter.
                onAddItem = {
                    count++
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        items(products) {product->
            ProductCard(
                product = product,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                onAddItem = {
                    count++
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            CartSummary(
                count = count,
            )
        }
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
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onAddItem: () -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Product Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(product.name)

            Spacer(modifier = Modifier.height(8.dp))

            Text("₹${product.price}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddItem
            ) {
                Text("Add Item")
            }
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
fun RKMartHeader(count: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("RKMart")
        Text("Cart : $count")
    }
}