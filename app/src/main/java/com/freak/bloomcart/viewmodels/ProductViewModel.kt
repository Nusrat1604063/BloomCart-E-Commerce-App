package com.freak.bloomcart.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freak.bloomcart.model.Product
import com.freak.bloomcart.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>> (emptyList())
    val products: StateFlow<List<Product>> = _products

    fun fetchProducts(categoryId: String) {
        viewModelScope.launch {
            try {
                val products = repository.getProductsByCategory(categoryId)
                _products.value = products
            }catch (e: Exception) {
                Log.e("TAGY", "Error fetching products")
            }
        }
    }

    private val _allProducts = MutableStateFlow<List<Product>> (emptyList())
    val allProducts: MutableStateFlow<List<Product>> =_allProducts

    fun getAllProductsInFireStore() {
        viewModelScope.launch {
            try {
                val allProducts = repository.getAllProductsInStore()
                println("Sucessful ${allProducts.size}")
                _allProducts.value = allProducts
            } catch (e: Exception) {
                Log.e("TAGY", "Error Fetching All Products")
            }
        }
    }

}