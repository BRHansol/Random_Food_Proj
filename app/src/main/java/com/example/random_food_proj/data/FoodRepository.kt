package com.example.random_food_proj.data

import android.util.Log
import com.example.random_food_proj.model.Food
import com.google.firebase.firestore.FirebaseFirestore

class FoodRepository {

    private val db = FirebaseFirestore.getInstance()
    fun getAllFoods(onResult: (List<Food>) -> Unit) {
        db.collection("foods")
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.map { doc ->
                    val food = doc.toObject(Food::class.java)
                    food.id = doc.id
                    food
                }
                onResult(list)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun addFood(food: Food) {
        db.collection("foods").add(food)
    }

    fun updateFood(food: Food) {
        db.collection("foods").document(food.id).set(food)
    }

//    fun getAllFoods(onResult: (List<Food>) -> Unit) {
//        db.collection("foods")
//            .addSnapshotListener { snapshot, error ->
//                if (error != null) {
//                    Log.e("FoodRepository", "getAllFoods listen failed.", error)
//                    onResult(emptyList())
//                    return@addSnapshotListener
//                }
//
//                val list = mutableListOf<Food>()
//                snapshot?.forEach { doc ->
//                    try {
//                        val food = doc.toObject(Food::class.java)
//                        food.id = doc.id
//                        list.add(food)
//                    } catch (e: Exception) {
//                        Log.e("FoodRepository", "Error converting doc to Food", e)
//                    }
//                }
//                // Always return the list, even if empty
//                onResult(list)
//            }
//    }

//    fun getByCategory(category: String, onResult: (List<Food>) -> Unit) {
//        db.collection("foods")
//            .whereEqualTo("category", category)
//            .addSnapshotListener { snapshot, error ->
//                if (error != null) {
//                    Log.e("FoodRepository", "getByCategory listen failed", error)
//                    onResult(emptyList())
//                    return@addSnapshotListener
//                }
//
//                val list = mutableListOf<Food>()
//                snapshot?.forEach { doc ->
//                    try {
//                        val food = doc.toObject(Food::class.java)
//                        food.id = doc.id
//                        list.add(food)
//                    } catch (e: Exception) {
//                        Log.e("FoodRepository", "Error converting doc to Food", e)
//                    }
//                }
//                onResult(list)
//            }
//    }


}