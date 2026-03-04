package com.example.random_food_proj.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.random_food_proj.data.FoodRepository
import com.example.random_food_proj.model.Food

class FoodViewModel : ViewModel() {
    private val repository = FoodRepository()

    val foodList = MutableLiveData<List<Food>>()
    private var allFoods: List<Food> = listOf()

    fun loadFoods() {
        if (allFoods.isNotEmpty()) return // ป้องกันโหลดซ้ำ

        repository.getAllFoods { list ->
            allFoods = list
            foodList.postValue(list)
        }
    }

    fun filterByCategory(category: String) {
        val filteredList = if (category == "All" || category == "ทั้งหมด") {
            allFoods
        } else {
            allFoods.filter { it.category == category }
        }
        foodList.value = filteredList
    }

    fun getRandomFood(selectedNames: List<String>): String? {
        return selectedNames.randomOrNull()
    }

//    fun loadByCategory(category: String) {
//        // 🔥 Handle both "All" and "ทั้งหมด" to fetch everything
//        if (category == "All" || category == "ทั้งหมด") {
//            repository.getAllFoods { list ->
//                allFoods = list
//                foodList.value = list
//            }
//        } else {
//            repository.getByCategory(category) { list ->
//                allFoods = list
//                foodList.value = list
//            }
//        }
//    }


}