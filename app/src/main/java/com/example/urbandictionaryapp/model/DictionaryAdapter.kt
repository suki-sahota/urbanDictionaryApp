package com.example.urbandictionaryapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionaryapp.R
import com.example.urbandictionaryapp.view.DefinitionVH
import java.util.*
import kotlin.Comparator

class DictionaryAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataSet: MutableList<DefinitionModel> = mutableListOf()
        set(value) { // Replaces whole dataSet with value
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DefinitionVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.definition_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DefinitionVH).onBind(dataSet[position])
    }

    fun sortThumbsUp() {
        dataSet.sortWith(Comparator { lhs, rhs ->
            if (lhs.thumbs_up > rhs.thumbs_up) -1
            else if (lhs.thumbs_up < rhs.thumbs_up) 1
            else 0 })
        notifyDataSetChanged()
    }

    fun sortThumbsDown() {
        dataSet.sortWith(Comparator { lhs, rhs ->
            if (lhs.thumbs_down > rhs.thumbs_down) -1
            else if (lhs.thumbs_down < rhs.thumbs_down) 1
            else 0 })
        notifyDataSetChanged()
    }

}