package com.example.echoemergency.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.echoemergency.R
import com.example.echoemergency.database.People

class PeopleRVAdapter(val context: Context, private val listener: IPeopleRVAdapter): RecyclerView.Adapter<PeopleRVAdapter.ViewHolder>() {

    private val allPeople = ArrayList<People>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val number: TextView = itemView.findViewById(R.id.tvNumber)
        val delete: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_people_item, parent, false))
        viewHolder.delete.setOnClickListener {
            //gets the position of the item clicked
            listener.onItemClicked(allPeople[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = allPeople[position]
        holder.name.text = currentPerson.name
        holder.name.text = currentPerson.number
    }

    override fun getItemCount(): Int {
        return allPeople.size
    }

    //this function is to tell recyclerview the changes observed by viewmodel
    fun updateList(newList: List<People>) {
        //first clearing the older list of people
        allPeople.clear()
        //adding the newList in which the changes are present
        allPeople.addAll(newList)
        //updating the recyclerview with changes
        notifyDataSetChanged()
    }


}

//handling clicks
interface IPeopleRVAdapter {
    fun onItemClicked(people: People)
}