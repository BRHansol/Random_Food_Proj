package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.random_food_proj.model.Food
import com.example.random_food_proj.ui.food.FoodViewModel

class RandomActivity : AppCompatActivity() {

    private lateinit var btnRandom: Button
    private lateinit var icBack: ImageView
    private lateinit var menuContainer: LinearLayout
    private lateinit var txtCategoryTitle: TextView
    private lateinit var viewModel: FoodViewModel
    private lateinit var icFilter: ImageView

    private var currentCategory: String = "ทั้งหมด"
    private var currentFoodList: List<Food> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        init()

        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        currentCategory = intent.getStringExtra("category") ?: "ทั้งหมด"
        txtCategoryTitle.text = currentCategory

        // โหลดข้อมูลทั้งหมด (ครั้งเดียว)
        viewModel.loadFoods()

        viewModel.foodList.observe(this) { list ->
            if (list.isEmpty()) {
                showEmpty()
                return@observe
            }

            // filter ใน Activity (หรือจะใช้ viewModel.filterByCategory ก็ได้)
            currentFoodList = if (currentCategory == "ทั้งหมด") {
                list
            } else {
                list.filter { it.category == currentCategory }
            }

            showFoods(currentFoodList)
        }

        btnRandom.setOnClickListener {
            val selectedNames = getSelectedFoods()

            if (selectedNames.isEmpty()) {
                Toast.makeText(this, "โปรดเลือกอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultRandomActivity::class.java)
            intent.putStringArrayListExtra(
                "selected_foods",
                ArrayList(selectedNames)
            )
            intent.putExtra("category", currentCategory)
            startActivity(intent)
        }

        icBack.setOnClickListener {
            finish()
        }
        icFilter.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        btnRandom = findViewById(R.id.btnRandom_Ran)
        icBack = findViewById(R.id.icBack)
        menuContainer = findViewById(R.id.menuContainer)
        txtCategoryTitle = findViewById(R.id.txtCategoryTitle)
        icFilter = findViewById(R.id.icFilter)
    }

    private fun showFoods(list: List<Food>) {
        menuContainer.removeAllViews()

        list.forEach { food ->

            // Card container
            val cardLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(32, 32, 32, 32)

                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 24)
                }

                setBackgroundResource(R.drawable.bg_food_card)
                elevation = 8f
            }

            val checkBox = CheckBox(this).apply {
                text = food.name
                textSize = 18f
                isChecked = true
                setPadding(16, 0, 0, 0)
            }

            cardLayout.addView(checkBox)
            menuContainer.addView(cardLayout)
        }
    }

    private fun showEmpty() {
        menuContainer.removeAllViews()
        val textView = TextView(this)
        textView.text = "ไม่พบข้อมูลในหมวดหมู่นี้"
        textView.textSize = 16f
        menuContainer.addView(textView)
    }

    private fun getSelectedFoods(): List<String> {
        val selected = mutableListOf<String>()

        for (i in 0 until menuContainer.childCount) {
            val cardLayout = menuContainer.getChildAt(i)

            if (cardLayout is LinearLayout) {
                val checkBox = cardLayout.getChildAt(0)

                if (checkBox is CheckBox && checkBox.isChecked) {
                    selected.add(checkBox.text.toString())
                }
            }
        }

        return selected
    }
}