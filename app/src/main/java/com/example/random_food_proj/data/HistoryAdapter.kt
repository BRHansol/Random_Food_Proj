package com.example.random_food_proj.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.random_food_proj.R

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1

class HistoryAdapter(private val list: List<HistoryModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        class HeaderViewHolder(view:View):RecyclerView.ViewHolder(view){

            val header =
                view.findViewById<TextView>(R.id.txtHeader)

        }

        class FoodViewHolder(view:View):RecyclerView.ViewHolder(view){

            val food =
                view.findViewById<TextView>(R.id.txtFood)

        }


        override fun getItemViewType(position: Int): Int {

            return list[position].type

        }


        override fun onCreateViewHolder(
            parent:ViewGroup,
            viewType:Int
        ):RecyclerView.ViewHolder{

            if(viewType == TYPE_HEADER){

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_history_date,parent,false)

                return HeaderViewHolder(view)

            }else{

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_history,parent,false)

                return FoodViewHolder(view)

            }

        }


        override fun getItemCount():Int{

            return list.size

        }


        override fun onBindViewHolder(
            holder:RecyclerView.ViewHolder,
            position:Int
        ){

            val item = list[position]

            if(holder is HeaderViewHolder){

                holder.header.text = item.text

            }

            if(holder is FoodViewHolder){

                holder.food.text = item.text

            }

        }


}