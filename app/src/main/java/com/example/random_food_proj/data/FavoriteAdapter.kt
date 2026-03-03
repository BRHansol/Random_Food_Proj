package com.example.random_food_proj.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.random_food_proj.R

class FavoriteAdapter {
    class FavoriteAdapter(private val list:List<Favorite>)
        : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


        class ViewHolder(view: View):RecyclerView.ViewHolder(view){

            val food =
                view.findViewById<TextView>(R.id.txtFood)

        }


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType:Int
        ):ViewHolder{

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite,parent,false)

            return ViewHolder(view)
        }


        override fun getItemCount():Int{

            return list.size
        }


        override fun onBindViewHolder(
            holder:ViewHolder,
            position:Int
        ){

            holder.food.text =
                list[position].foodName

        }

    }
}