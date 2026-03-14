package com.freak.bloomcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.freak.bloomcart.screens.cart.CartScreen
import com.freak.bloomcart.screens.category.CategoryScreen
import com.freak.bloomcart.screens.home.HomeScreen
import com.freak.bloomcart.screens.navigation.Screens
import com.freak.bloomcart.screens.products.ProductDetalsScreen
import com.freak.bloomcart.screens.products.ProductScreen
import com.freak.bloomcart.screens.profile.ProfileScreen
import com.freak.bloomcart.screens.profile.SignUpScreen
import com.freak.bloomcart.ui.theme.BloomCartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            //Nav Host
          NavHost(navController = navController, //nav Host is the core component that manages navigation  between screens
              startDestination = Screens.Home.route
              ) {
                //define routues using composable() {}
              //for each screen you want to support

              composable(Screens.Home.route) {
                  HomeScreen(
                      navController = navController,
                      onProfileClick = {  navController.navigate(Screens.Profile.route)},
                      onCartClick = {navController.navigate(Screens.cart.route)}

                  )
              }

              composable(Screens.cart.route) {
                  CartScreen(navController)
              }

              composable(Screens.Profile.route) {
                  ProfileScreen(navController = navController, onSignOut = {})
              }

              composable(Screens.CategoryList.route ) {
                  CategoryScreen(navController)
              }

              composable(Screens.ProductDetails.route) {
                    val productId = it.arguments?.getString("productId")
                  if(productId != null) {
                      ProductDetalsScreen(productId)
                  }
              }

              composable(Screens.ProductList.route) {
                  val categoryId = it.arguments?.getString("categoryId")
                  if(categoryId != null) {
                      ProductScreen(categoryId, navController = navController)
                  }
              }

              composable(Screens.SignUp.route) {
                  SignUpScreen(
                      onNavigateToLogin = {
                          navController.navigate(Screens.Login.route)
                      },
                      onSignUpSuccess = {
                        navController.navigate(Screens.Home.route)
                      }
                  )
              }

          }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BloomCartTheme {
        Greeting("Android")
    }
}