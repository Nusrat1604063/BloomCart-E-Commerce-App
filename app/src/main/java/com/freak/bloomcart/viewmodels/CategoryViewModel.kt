package com.freak.bloomcart.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freak.bloomcart.model.Category
import com.freak.bloomcart.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> get() = _categories


    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {

            repository.getCategoriesFlow()
                .catch {
                    // Handle any errors
                    println("Error in Flow")
                }
                .collect { categories ->
                    // Update the stateflow with new data
                    _categories.value = categories
                    println("categories updated in Viewmodel")
                }
        }
    }

}