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
import com.example.random_food_proj.data.MenuList
import com.example.random_food_proj.data.MenuListAdapter

class MymenuList : AppCompatActivity() {
    private lateinit var btnMenu: Button
    private lateinit var btnFavorite: Button
    private lateinit var btnHistory: Button
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mymenu_list)

        // Initialize views AFTER setContentView
        btnMenu = findViewById(R.id.btnMenu)
        btnFavorite = findViewById(R.id.btnFavorite)
        btnHistory = findViewById(R.id.btnHistory)
        recycler = findViewById(R.id.recyclerMenu)

        val list = ArrayList<MenuList>()
        list.add(MenuList("ข้าวเช้า", 5))
        list.add(MenuList("ข้าวเที่ยง", 3))

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = MenuListAdapter(list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMenu.setOnClickListener {
            // Already on MenuList
        }

        btnFavorite.setOnClickListener {
            val intent = Intent(this, favorite::class.java)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        
        setupBottomBar()
    }

    private fun setupBottomBar() {
        findViewById<ImageView>(R.id.btnAdd)?.setOnClickListener {
            val intent = Intent(this, AddMenuActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnHome)?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnProfile)?.setOnClickListener {
            // Already on Profile (MenuList)
        }
    }
}