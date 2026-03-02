package com.example.random_food_proj.data

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.random_food_proj.R
import com.example.random_food_proj.model.Food
import com.google.firebase.firestore.FirebaseFirestore

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val food = Food("T01","ข้าวผัด", "Thaifood")
        db.collection("foods")
            .document("T05")
            .set(food)
            .addOnSuccessListener {
                println("เพิ่มข้อมูลสำเร็จ")
            }
            .addOnFailureListener {
                println("เกิดข้อผิดพลาด: ${it.message}")
            }
    }
}