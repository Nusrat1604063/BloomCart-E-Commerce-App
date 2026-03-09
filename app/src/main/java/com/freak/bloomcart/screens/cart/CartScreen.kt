package com.freak.bloomcart.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freak.bloomcart.model.Product
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun CartScreen(navController: NavController) {

    val cartItems = listOf(
        Product("3", "SmartPhone", 999.99, "https://m-cdn.phonearena.com/images/hub/404-wide-two_1200/Apple-iPhone-15-release-date-price-and-features.jpg")
    )
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        if(cartItems.isEmpty()) {
           //
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Your cart is Empty", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {}) {
                    Text(text = "Continue Shopping")
                }
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {

                items(cartItems) { items ->
                   CartItemCard(
                       item = items,
                       onRemoveItem = {}
                   )
                }



            }

            Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(text = "Total:",
                        style = MaterialTheme.typography.titleMedium)

                    Text(text = "...$",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold)

                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {}, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                    Text(text = "Proceed to Checkout")
                }
            }
        }

    }

}