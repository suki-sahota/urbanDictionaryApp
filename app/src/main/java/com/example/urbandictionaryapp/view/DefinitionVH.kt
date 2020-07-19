package com.example.urbandictionaryapp.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionaryapp.model.DefinitionModel
import kotlinx.android.synthetic.main.definition_item_layout.view.*

class DefinitionVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvDefinition = itemView.tv_definition
    val tvThumbsUp = itemView.tv_thumbs_up
    val tvThumbsDown = itemView.tv_thumbs_down

    fun onBind(definitionItem: DefinitionModel) {
        tvDefinition.text = definitionItem.definition
        tvThumbsUp.text = definitionItem.thumbs_up.toString()
        tvThumbsDown.text = definitionItem.thumbs_down.toString()
    }
}