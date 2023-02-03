package com.example.tickytodo.adapter


import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodo.R
import com.example.tickytodo.dataClasses.HomeData
import com.example.tickytodo.database.Task
import com.example.tickytodo.databinding.ItemTaskLayoutBinding

class MyTaskAdapter() :
    RecyclerView.Adapter<MyTaskAdapter.ViewHolder>() {

    private var toDoList = ArrayList<HomeData>()

    private val images = arrayOf(
        R.drawable.ic_red_oval,
        R.drawable.ic_green_oval,
        R.drawable.ic_blue_oval,
        R.drawable.ic_light_purple_oval,
        R.drawable.ic_orange_oval,
        R.drawable.ic_pink_oval,
        R.drawable.ic_purple_oval,
        R.drawable.ic_yellow_oval,
        R.drawable.ic_dark_blue_oval
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskLayoutBinding.bind(view)

        val descriptionSample = itemView.findViewById<TextView>(R.id.descriptionSample)
        val checkboxSample = itemView.findViewById<CheckBox>(R.id.checkboxSample)
        val dateSample = itemView.findViewById<TextView>(R.id.dateSample)
        val colorImageView = itemView.findViewById<ImageView>(R.id.small_red_circle)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = toDoList[position]
        holder.descriptionSample.text = todo.description
        holder.dateSample.text = todo.date
        holder.checkboxSample.isChecked = todo.checkBox

        holder.colorImageView.setImageResource(images[todo.color])
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    fun showInfo(taskList:List<HomeData>){
        this.toDoList.clear()
        this.toDoList.addAll(taskList)
        notifyDataSetChanged()
    }
}




