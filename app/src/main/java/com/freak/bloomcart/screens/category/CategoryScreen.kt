package com.freak.bloomcart.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.freak.bloomcart.model.Category
import org.w3c.dom.Text
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.StrokeCap
import com.freak.bloomcart.screens.navigation.Screens

@Composable
fun CategoryScreen(
    navController: NavController
) {
    val categories : List<Category> = listOf(
        Category(1, "Electronics","https://cdn-icons-png.flaticon.com/512/5754/5754910.png"),
        Category(2, "Clothing",
            "https://static.thenounproject.com/png/524455-200.png"),
        Category(3,"Cosmetics", "https://static.thenounproject.com/png/20253-200.png")
    )

    Column() {
        if(categories.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
                ) {

                Text(text = "No Categories found",
                    style = MaterialTheme.typography.bodyLarge)

            }
        } else {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            )

            //categories Greed
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                items(categories) { category ->

                    CategoryItem(
                        category = category,
                        onClick = {
                            navController.navigate(Screens.ProductList
                                .createRoute(category.id.toString()))
                        }
                    )

                }
            }
        }
    }


}