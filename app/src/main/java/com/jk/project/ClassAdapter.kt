package com.jk.project

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.jk.project.databinding.ItemClassListBinding

class ClassAdapter(context: Context, var dataSource : ArrayList<Class>)
    : ArrayAdapter<Class>(context, 0, dataSource) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentClass = getItem(position)

        lateinit var itemClassListBinding: ItemClassListBinding
        itemClassListBinding = ItemClassListBinding.inflate(LayoutInflater.from(context),
            parent,
            false)

        var itemView = itemClassListBinding.root

        if (currentClass != null) {
            itemClassListBinding.tvTitle.setText(currentClass.title)
            itemClassListBinding.tvDetail.setText(currentClass.detail)
            itemClassListBinding.imgClassId.setImageResource(currentClass.imgClassId)
            if (!currentClass.isComplete) {
                itemClassListBinding.imgCheckMark.setImageDrawable(null)
            }
        }


        return itemView
    }
}











