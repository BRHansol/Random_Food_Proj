package com.example.random_food_proj

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.random_food_proj.model.Food
import com.google.firebase.firestore.FirebaseFirestore

class AddMenuActivity : AppCompatActivity() {

    private lateinit var edtNameList: EditText
    private lateinit var edtItem1: EditText
    private lateinit var edtItem2: EditText
    private lateinit var btnAddMenu: Button
    private lateinit var icBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        icBack.setOnClickListener {
            finish()
        }

        btnAddMenu.setOnClickListener {
            saveMenu()
        }
    }

    private fun init() {
        edtNameList = findViewById(R.id.edtnameList)
        edtItem1 = findViewById(R.id.edtitem1)
        edtItem2 = findViewById(R.id.edtitem2)
        btnAddMenu = findViewById(R.id.btnAddmenu)
        icBack = findViewById(R.id.imageView2)
    }

    private fun saveMenu() {
        val category = edtNameList.text.toString().trim()
        val item1 = edtItem1.text.toString().trim()
        val item2 = edtItem2.text.toString().trim()

        if (category.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกชื่อลิสต์ (หมวดหมู่)", Toast.LENGTH_SHORT).show()
            return
        }

        val db = FirebaseFirestore.getInstance()
        val itemsToSave = mutableListOf<String>()
        if (item1.isNotEmpty()) itemsToSave.add(item1)
        if (item2.isNotEmpty()) itemsToSave.add(item2)

        if (itemsToSave.isEmpty()) {
            Toast.makeText(this, "กรุณากรอกอย่างน้อย 1 รายการ", Toast.LENGTH_SHORT).show()
            return
        }

        var successCount = 0
        val totalToSave = itemsToSave.size

        for (itemName in itemsToSave) {
            val food = Food(name = itemName, category = category)
            db.collection("foods")
                .add(food)
                .addOnSuccessListener {
                    successCount++
                    if (successCount == totalToSave) {
                        Toast.makeText(this, "บันทึกข้อมูลสำเร็จ!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("AddMenuActivity", "Error adding document", e)
                    Toast.makeText(this, "เกิดข้อผิดพลาด: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}