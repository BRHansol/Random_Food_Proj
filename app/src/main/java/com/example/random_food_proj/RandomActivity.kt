package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.random_food_proj.ui.food.FoodViewModel

class RandomActivity : AppCompatActivity() {
    private lateinit var btnRandom_ran : Button
    private lateinit var icBack : ImageView
    private lateinit var menuContainer: LinearLayout
    private lateinit var txtCategoryTitle: TextView
    private lateinit var viewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_random)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
        
        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        val category = intent.getStringExtra("category") ?: "อาหารไทย"
        txtCategoryTitle.text = category
        
        Log.d("RandomActivity", "Searching for category: $category")

        // 1. Only load if not already loading to avoid double calls
        viewModel.loadByCategory(category)

        // 2. Observe data changes
        viewModel.foodList.observe(this) { list ->
            Log.d("RandomActivity", "Observer triggered with ${list.size} items")
            
            // Clear and rebuild UI on the main thread
            menuContainer.post {
                menuContainer.removeAllViews()
                
                if (list.isEmpty()) {
                    val emptyText = TextView(this).apply {
                        text = "ไม่พบข้อมูลในหมวดหมู่ $category"
                        textSize = 16f
                        setPadding(40, 40, 40, 40)
                    }
                    menuContainer.addView(emptyText)
                } else {
                    list.forEach { food ->
                        val checkBox = CheckBox(this).apply {
                            text = food.name
                            textSize = 18f
                            isChecked = true
                            setPadding(16, 16, 16, 16)
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            ).apply {
                                setMargins(0, 0, 0, 16)
                            }
                            setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
                        }
                        menuContainer.addView(checkBox)
                    }
                }
            }
        }

        btnRandom_ran.setOnClickListener {
            val selectedFoods = mutableListOf<String>()
            for (i in 0 until menuContainer.childCount) {
                val view = menuContainer.getChildAt(i)
                if (view is CheckBox && view.isChecked) {
                    selectedFoods.add(view.text.toString())
                }
            }

            if (selectedFoods.isNotEmpty()) {
                val randomResult = selectedFoods.random()
                val intent = Intent(this, ResultRandomActivity::class.java)
                intent.putExtra("food_name", randomResult)
                // Pass the list of selected foods for re-randomizing in the next page
                intent.putStringArrayListExtra("selected_foods", ArrayList(selectedFoods))
                startActivity(intent)
            } else {
                Toast.makeText(this, "โปรดเลือกอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show()
            }
        }
        icBack.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        icBack = findViewById(R.id.icBack)
        btnRandom_ran = findViewById(R.id.btnRandom_Ran)
        menuContainer = findViewById(R.id.menuContainer)
        txtCategoryTitle = findViewById(R.id.txtCategoryTitle)
    }
}