package com.example.echoemergency.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.echoemergency.database.Number

class NumberDiffUtil(private val oldList: List<Number>, private val newList: List<Number>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].number != newList[newItemPosition].number -> {
                false
            }
            else -> true
        }
    }
}