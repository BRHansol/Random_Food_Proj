package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupCategoryButtons()
        setupBottomBar()

        // Handle "สุ่มเลยไม่ต้องคิด!" Button (Random All)
        findViewById<Button>(R.id.btnRandom_Ran)?.setOnClickListener {
            val intent = Intent(this, ResultRandomActivity::class.java)
            // Tell the result page to random from "everything"
            intent.putExtra("mode", "RANDOM_ALL")
            startActivity(intent)
        }
    }

    private fun setupCategoryButtons() {
        val categories = mapOf(
            R.id.btnclean_food to "อาหารคลีน",
            R.id.btnthai_food to "อาหารไทย",
            R.id.btnjp_food to "อาหารญี่ปุ่น",
            R.id.btnvegen_food to "อาหารเจ",
            R.id.btncn_food to "อาหารจีน",
            R.id.btnitalian_food to "อาหารอิตาลี"
        )

        for ((id, name) in categories) {
            findViewById<FrameLayout>(id)?.setOnClickListener {
                val intent = Intent(this, RandomActivity::class.java)
                intent.putExtra("category", name)
                startActivity(intent)
            }
        }
    }

    private fun setupBottomBar() {
        findViewById<ImageView>(R.id.btnAdd)?.setOnClickListener {
            val intent = Intent(this, AddMenuActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnHome)?.setOnClickListener {
            // Already on Home
        }

        findViewById<ImageView>(R.id.btnProfile)?.setOnClickListener {
            val intent = Intent(this, MymenuList::class.java)
            startActivity(intent)
        }
    }
}