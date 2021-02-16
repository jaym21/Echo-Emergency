package com.example.echoemergency.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.echoemergency.R
import com.example.echoemergency.database.Number

class NumberRVAdapter(val context: Context, private val listener: INumberRVAdapter): RecyclerView.Adapter<NumberRVAdapter.ViewHolder>() {

    private val allNumbers = ArrayList<Number>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.tvNumber)
        val delete: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_number_item, parent, false))
        viewHolder.delete.setOnClickListener {
            //gets the position of the item clicked
            listener.onItemClicked(allNumbers[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = allNumbers[position]
        holder.number.text = currentPerson.number
    }

    override fun getItemCount(): Int {
        return allNumbers.size
    }

    //this function is to tell recyclerview the changes observed by viewModel
    fun updateList(newList: List<Number>) {
        //first clearing the older list of numbers
        allNumbers.clear()
        //adding the newList in which the changes are present
        allNumbers.addAll(newList)
        //updating the recyclerview with changes
        notifyDataSetChanged()
    }


}

//handling clicks
interface INumberRVAdapter {
    fun onItemClicked(number: Number)
}