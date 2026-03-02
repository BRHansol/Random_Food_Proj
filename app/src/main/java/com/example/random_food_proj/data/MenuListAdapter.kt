package com.example.random_food_proj.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.random_food_proj.R
import com.example.random_food_proj.data.MenuList


class MenuListAdapter(private val list: List<MenuList>)
    : RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val name = view.findViewById<TextView>(R.id.txtName)

        val count = view.findViewById<TextView>(R.id.txtCount)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menulist, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return list.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val menu = list[position]

        holder.name.text = menu.name

        holder.count.text =
            menu.count.toString() + " รายการ"

    }

}