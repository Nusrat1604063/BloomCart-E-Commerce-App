package com.freak.bloomcart.screens.home

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fr.CategoryChip
import com.freak.bloomcart.model.Category
import com.freak.bloomcart.model.Product
import com.freak.bloomcart.viewmodels.CategoryViewModel
import com.freak.bloomcart.viewmodels.ProductViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.freak.bloomcart.screens.navigation.Screens


@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick: ()-> Unit,
    onCartClick: ()-> Unit,
    productViewModel: ProductViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel()
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
            SectionTitle("Categories", "See All") {
                //Add navigation
                navController.navigate(Screens.CategoryList.route)
            }

            //featured products
            Spacer(modifier = Modifier.height(16.dp))


           val categoriesState = categoryViewModel.categories.collectAsState()
            val categories = categoriesState.value


            val selectedCategory = remember { mutableStateOf(0) }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) {
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value = categories[it].id
                            /** Do the navigation logic **/
                            navController.navigate(
                                Screens.ProductList.createRoute(selectedCategory.value.toString())
                            )

                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SectionTitle("Featured", "See All") {
               navController.navigate(Screens.CategoryList.route)
            }

           productViewModel.getAllProductsInFireStore()
            val allProductsState = productViewModel.allProducts.collectAsState()
            val allproductsFound = allProductsState.value

            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                items(allproductsFound) {
                        product ->
                    FeaturedProductCard(product) {
                      navController.navigate(
                          Screens.ProductDetails.createRoute(product.id)
                      )
                    }
                }

            }
        }
    }
}