package com.example.random_food_proj

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.random_food_proj.ui.food.FoodViewModel
import com.google.android.material.button.MaterialButton

class ResultRandomActivity : AppCompatActivity() {

    private lateinit var txtFoodName: TextView
    private lateinit var btnRandomRes: MaterialButton
    private lateinit var icBack: ImageView
    private lateinit var txtTitle: TextView
    private lateinit var viewModel: FoodViewModel

    private var selectedFoods: ArrayList<String> = arrayListOf()
    private var mode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_random)

        init()

        viewModel = ViewModelProvider(this)[FoodViewModel::class.java]

        mode = intent.getStringExtra("mode")

        if (mode == "RANDOM_ALL") {
            handleRandomAll()
        } else {
            handleNormalRandom()
        }

        icBack.setOnClickListener { finish() }

        btnRandomRes.setOnClickListener {
            if (selectedFoods.isEmpty()) {
                Toast.makeText(this, "ไม่มีรายการให้สุ่ม", Toast.LENGTH_SHORT).show()
            } else {
                showRandomWithDelay()
            }
        }
    }

    private fun handleRandomAll() {
        txtTitle.text = "สุ่มทั้งหมด"
        txtFoodName.text = "กำลังสุ่ม..."

        viewModel.loadFoods()

        viewModel.foodList.observe(this) { list ->
            if (list.isNotEmpty()) {
                selectedFoods = ArrayList(list.map { it.name })
                showRandomWithDelay()
                viewModel.foodList.removeObservers(this)
            } else {
                txtFoodName.text = "ไม่พบข้อมูล"
            }
        }
    }

    private fun handleNormalRandom() {
        val category = intent.getStringExtra("category") ?: "สุ่มเมนู"
        selectedFoods =
            intent.getStringArrayListExtra("selected_foods") ?: arrayListOf()

        txtTitle.text = category

        if (selectedFoods.isEmpty()) {
            txtFoodName.text = "ไม่พบข้อมูล"
        } else {
            showRandomWithDelay()
        }
    }

    private fun showRandomWithDelay() {
        txtFoodName.text = "กำลังสุ่ม..."
        btnRandomRes.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            val result = selectedFoods.randomOrNull()
            txtFoodName.text = result ?: "ไม่พบข้อมูล"
            btnRandomRes.isEnabled = true
        }, 1000)
    }

    private fun init() {
        txtFoodName = findViewById(R.id.txtFoodName)
        btnRandomRes = findViewById(R.id.btnRandom_Res)
        icBack = findViewById(R.id.icBack)
        txtTitle = findViewById(R.id.txtTitle)
    }
}