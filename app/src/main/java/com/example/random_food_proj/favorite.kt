package com.example.random_food_proj

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.random_food_proj.data.Favorite
import com.example.random_food_proj.data.FavoriteAdapter

class favorite : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)
        recycler = findViewById(R.id.recyclerFavorite)
        val list = ArrayList<Favorite>()
        list.add(Favorite("ต้มยำกุ้ง"))
        list.add(Favorite("ยำวุ้นเส้น"))
        list.add(Favorite("ส้มตำปลาร้า"))
        list.add(Favorite("ข้าวไข่เจียว"))
        list.add(Favorite("ผะโล้หมู"))
        recycler.layoutManager =
            LinearLayoutManager(this)
        recycler.adapter =
            FavoriteAdapter.FavoriteAdapter(list)

        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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