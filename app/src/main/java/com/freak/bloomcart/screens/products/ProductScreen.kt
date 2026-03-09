package com.freak.bloomcart.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.freak.bloomcart.model.Product
import androidx.compose.foundation.lazy.items
import com.freak.bloomcart.screens.navigation.Screens

@Composable
fun ProductScreen(
    categoryId: String,
    navController: NavController
) {
    //fetch product from the viewmodel

   //Collect products from viewmodel
    val products = listOf(
        Product("1","Smartphone", 999.99, "https://m-cdn.phonearena.com/images/hub/404-wide-two_1200/Apple-iPhone-15-release-date-price-and-features.jpg",
            "Electronics"),
        Product(id = "2", "Gadgets", 350.50, "https://photos5.appleinsider.com/gallery/product_pages/131-hero.jpg"),
        Product(id = "3", "Tv", 350.50, "https://photos5.appleinsider.com/gallery/product_pages/131-hero.jpg")
    )

    //display the products

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Products of Category ID: $categoryId",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        if(products.isEmpty()) {
            Text(text = "No products Found!",
                modifier = Modifier.padding(16.dp))
        }else {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
                ) {
                items(products) { product ->
                    ProductItem(product = product,
                        onCLick = {
                            navController.navigate(
                                Screens.ProductDetails.createRoute(product.id))
                        },
                        onAddToCart = {
                            //add the product o cart
                            //room database insertion
                        }
                    )

                }
            }
        }
    }


}