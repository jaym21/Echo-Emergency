package com.example.echoemergency.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.echoemergency.R
import com.example.echoemergency.models.Number
import com.example.echoemergency.utils.NumberDiffUtil

class NumberRVAdapter(val context: Context, private val listener: INumberRVAdapter): RecyclerView.Adapter<NumberRVAdapter.ViewHolder>() {

    private var allNumbers = emptyList<Number>()
    private val lengthLimit = 5

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
//        return if(allNumbers.size > lengthLimit) {
//            lengthLimit
//        } else{
//            allNumbers.size
//        }
        return allNumbers.size
    }

    //this function is to tell recyclerview the changes observed by viewModel
//    fun updateList(newList: List<Number>) {
//        //first clearing the older list of numbers
//        allNumbers.clear()
//        //adding the newList in which the changes are present
//        allNumbers.addAll(newList)
//        //updating the recyclerview with changes
//        notifyDataSetChanged()
//    }

    fun updateList(newList: List<Number>) {
        val diffUtil = NumberDiffUtil(allNumbers, newList)
        //Calculates the list of update operations that can covert one list into the other one
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        //first replacing oldList with the new Updated List for next Updates
        allNumbers = newList
        //updating the list
        diffResults.dispatchUpdatesTo(this)

    }


}

//handling clicks
interface INumberRVAdapter {
    fun onItemClicked(number: Number)
}