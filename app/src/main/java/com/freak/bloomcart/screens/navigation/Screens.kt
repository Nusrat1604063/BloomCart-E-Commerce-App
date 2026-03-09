package com.freak.bloomcart.screens.navigation

sealed class Screens(val route: String) {
    object cart : Screens("Cart")
    object ProductDetails: Screens("product_details/{productId") {
        fun createRoute(productId: String) = "product_details/$productId" //navigate with arguments
    }
    object Profile: Screens("Profile")
    object ProductList: Screens("product_list/{categoryId}") {
        fun createRoute(categoryId: String) = "product_list/$categoryId"
    }

    object CategoryList: Screens("category_list")
    object Login: Screens("Login")
    object SignUp: Screens("SignUp")
    object Home: Screens("Home ")
}