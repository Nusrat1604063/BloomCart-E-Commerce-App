package com.freak.bloomcart.repositories

import android.util.Log
import com.freak.bloomcart.model.Category
import com.freak.bloomcart.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
)  {

    //It only starts running when collected things like live updates

    fun getCategoriesFlow() : Flow<List<Category>> =
        callbackFlow {

            callbackFlow {

                val listenerRegistration = firestore
                    .collection("categories")
                    .addSnapshotListener { snapshot, error ->

                        if (error != null) {
                            println("Error fetching categories: ${error.message}")
                            return@addSnapshotListener
                        }

                        if (snapshot != null) {
                            // handle snapshot data here
                            val categories = snapshot.toObjects(Category::class.java)
                            trySend(categories)
                        }
                    }
                //Close teh flow when the listener is no longer needed
                awaitClose {
                    listenerRegistration.remove()
                }
            }
    }

    suspend fun getProductsByCategory(categoryId: String) : List<Product> {
        return try {
            val result = firestore.collection("products")
                .whereEqualTo("categoryId", categoryId)
                .get()
                .await()

            result.toObjects(Product::class.java).also {
                Log.v("TAG", "Mapped products: $it")
            }
        }catch (e: Exception) {
            emptyList()
        }
    }
}