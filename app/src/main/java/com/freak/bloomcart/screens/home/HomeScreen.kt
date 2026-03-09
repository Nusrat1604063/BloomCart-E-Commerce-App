package com.freak.bloomcart.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fr.CategoryChip
import com.freak.bloomcart.model.Category
import com.freak.bloomcart.model.Product
import com.freak.bloomcart.screens.BottomNavigationBar
import com.freak.bloomcart.screens.MyTopAppBar
import com.freak.bloomcart.screens.SearchBar


@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick: ()-> Unit,
    onCartClick: ()-> Unit
    ) {
    Scaffold(
        topBar = { MyTopAppBar(onProfileClick, onCartClick) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            //Search section

            val searchQuery = remember { mutableStateOf("") }
            val focusManager  = LocalFocusManager.current

            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value = it},
                onSearch = {
                    //later we'll call the viewmodels to get and call the serach product function
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            //Search result sections

            //category sections
            SectionTitle("Categories", "View All") {
                //Add navigation
                navController.navigate("Categories")
            }

            //featured products
            Spacer(modifier = Modifier.height(16.dp))


            val categories : List<Category> = listOf(
                Category(1, "Electronics","https://cdn-icons-png.flaticon.com/512/5754/5754910.png"),
                Category(2, "Clothing",
                    "https://static.thenounproject.com/png/524455-200.png"),
                Category(3,"Cosmetics", "https://static.thenounproject.com/png/20253-200.png")
                )


            val selectedCategory = remember { mutableStateOf(0) }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) {
                    CategoryChip(
                        icon = categories[it].iconURL,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value = it
                            /** Do the navigation logic **/
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SectionTitle("Featured", "See All") {
                //Add navigation
            }

            val productList = listOf (
                Product("1","Smartphone", 999.99, "https://m-cdn.phonearena.com/images/hub/404-wide-two_1200/Apple-iPhone-15-release-date-price-and-features.jpg",
                    "Electronics"),
                Product(id = "2", "Gadgets", 350.50, "https://photos5.appleinsider.com/gallery/product_pages/131-hero.jpg")

            )

            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                items(productList) {
                        product ->
                    FeaturedProductCard(product) {
                        //handle click event here
                    }
                }

            }
        }
    }
}