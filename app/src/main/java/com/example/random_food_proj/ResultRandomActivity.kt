package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ResultRandomActivity : AppCompatActivity() {

    private lateinit var txtFoodName: TextView
    private lateinit var btnRandomRes: MaterialButton
    private lateinit var icBack: ImageView
    private var selectedFoods: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_random)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        // 1. Retrieve the random result and the full list passed from RandomActivity
        val foodName = intent.getStringExtra("food_name") ?: "ไม่พบข้อมูล"
        selectedFoods = intent.getStringArrayListExtra("selected_foods")
        
        // 2. Show the initial result
        txtFoodName.text = foodName

        // Handle Back button
        icBack!!.setOnClickListener {
            finish()
        }

        // Handle "Random Again" button - NOW STAYS ON PAGE
        btnRandomRes!!.setOnClickListener {
            if (!selectedFoods.isNullOrEmpty()) {
                // Pick a new random item from the list we received
                val newResult = selectedFoods!!.random()
                txtFoodName.text = newResult
            } else {
                Toast.makeText(this, "ไม่มีรายการให้สุ่มใหม่", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        txtFoodName = findViewById(R.id.txtFoodName)
        btnRandomRes = findViewById(R.id.btnRandom_Res)
        icBack = findViewById(R.id.icBack)
    }
}