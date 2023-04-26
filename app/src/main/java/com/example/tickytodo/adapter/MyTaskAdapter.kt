package com.example.tickytodo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.databinding.ItemTaskLayoutBinding
import kotlinx.android.synthetic.main.item_task_layout.view.*

class MyTaskAdapter :
    RecyclerView.Adapter<MyTaskAdapter.ViewHolder>() {

    private var toDoList = ArrayList<Task>()
    private var selectedTasks = ArrayList<Task>()
    private val images = arrayOf(
        R.drawable.ic_red_oval,
        R.drawable.ic_orange_oval,
        R.drawable.ic_yellow_oval,
        R.drawable.ic_green_oval,
        R.drawable.ic_blue_oval,
        R.drawable.ic_dark_blue_oval,
        R.drawable.ic_purple_oval,
        R.drawable.ic_light_purple_oval,
        R.drawable.ic_pink_oval
    )

    inner class ViewHolder(
        view: View,

        ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskLayoutBinding.bind(view)

        val descriptionSample = binding.descriptionSample
        val checkboxSample = binding.checkboxSample
        val dateSample = binding.dateSample
        val colorImageView = binding.smallRedCircle
        val selectedOverlay = binding.overlay

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
        holder.checkboxSample.isChecked = todo.checkbox
        holder.colorImageView.setImageResource(images[todo.color])



        holder.itemView.itemLayout.setOnClickListener {
            listener?.setDataToUpdateFragment(todo)
        }
        holder.itemView.itemLayout.setOnLongClickListener {
            listener?.longClick(todo)
            selectedTasks.add(todo)
            holder.selectedOverlay.setBackgroundColor(
                if (selectedTasks.contains(todo)){
                    holder.itemView.context.resources.getColor(R.color.light_blue)
                }else{
                    holder.itemView.context.resources.getColor(android.R.color.white)
                })
            true
        }
        holder.checkboxSample.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                listener?.checkBox(todo.id!!,true)
            }
        }
        holder.selectedOverlay.setBackgroundColor(
            if (selectedTasks.contains(todo)) {
                holder.itemView.context.resources.getColor(R.color.light_blue)
            } else {
                holder.itemView.context.resources.getColor(android.R.color.white)
            }
        )

    }

    override fun getItemCount() = toDoList.size



    @SuppressLint("NotifyDataSetChanged")
    fun showInfo(taskList: List<Task>) {
        this.toDoList.clear()
        this.toDoList.addAll(taskList)
        notifyDataSetChanged()
    }

    private var listener: ISetDataToUpdateFragment? = null

    interface ISetDataToUpdateFragment {
        fun setDataToUpdateFragment(user: Task)
        fun longClick(user: Task)
        fun isRecyclerEmpty(isEmpty: Boolean)
        fun checkBox(id: Int, checked: Boolean)

    }

    fun impInterface(myListener: ISetDataToUpdateFragment) {
        this.listener = myListener
    }

    fun clearSelectedTasks() {
        selectedTasks.clear()
        notifyDataSetChanged()
    }
}




