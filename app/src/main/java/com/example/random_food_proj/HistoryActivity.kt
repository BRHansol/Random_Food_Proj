package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.random_food_proj.data.HistoryAdapter
import com.example.random_food_proj.data.HistoryModel
import com.example.random_food_proj.data.TYPE_HEADER
import com.example.random_food_proj.data.TYPE_ITEM

class HistoryActivity : AppCompatActivity() {
    val btnMenu = findViewById<Button>(R.id.btnMenu)
    val btnFavorite = findViewById<Button>(R.id.btnFavorite)
    val btnHistory = findViewById<Button>(R.id.btnHistory)
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        val list = ArrayList<HistoryModel>()

        list.add(HistoryModel(TYPE_HEADER,"12 ม.ค. 69"))
        list.add(HistoryModel(TYPE_ITEM,"ต้มยำกุ้ง"))

        list.add(HistoryModel(TYPE_HEADER,"11 ม.ค. 69"))
        list.add(HistoryModel(TYPE_ITEM,"ยำวุ้นเส้น"))
        list.add(HistoryModel(TYPE_ITEM,"ส้มตำปลาร้า"))
        list.add(HistoryModel(TYPE_ITEM,"ผัดไทย"))

        list.add(HistoryModel(TYPE_HEADER,"10 ม.ค. 69"))
        list.add(HistoryModel(TYPE_ITEM,"ข้าวไข่เจียว"))
        list.add(HistoryModel(TYPE_ITEM,"พะโล้หมู"))
        list.add(HistoryModel(TYPE_ITEM,"ข้าวซอย"))
        list.add(HistoryModel(TYPE_ITEM,"ปอเปี๊ยะทอด"))

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = HistoryAdapter(list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnMenu.setOnClickListener {

            val intent = Intent(this, MymenuList::class.java)
            startActivity(intent)

        }

        btnFavorite.setOnClickListener {

            val intent = Intent(this, favorite::class.java)
            startActivity(intent)

        }

        btnHistory.setOnClickListener {

            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)

        }
    }
    private fun setupBottomBar() {
        // Handle the Add button in the bottom bar
        findViewById<ImageView>(R.id.btnAdd)?.setOnClickListener {
            val intent = Intent(this, AddMenuActivity::class.java)
            startActivity(intent)
        }

        // Handle Home button (stays here or refreshes)
        findViewById<ImageView>(R.id.btnHome)?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Add logic for Profile if you have a ProfileActivity
        findViewById<ImageView>(R.id.btnProfile)?.setOnClickListener {
            val intent = Intent(this, MymenuList::class.java)
            startActivity(intent)
        }
    }
}