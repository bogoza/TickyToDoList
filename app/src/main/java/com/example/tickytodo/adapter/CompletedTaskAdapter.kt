package com.example.tickytodo.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.databinding.CompletedItemTaskBinding

class CompletedTaskAdapter:
    RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder>() {

    private var completedList = emptyList<Task>()



    inner class ViewHolder(
        view: View,

        ) : RecyclerView.ViewHolder(view) {
            private val binding = CompletedItemTaskBinding.bind(view)

            val textviewForCompleted = binding.textViewForCompleted
            val checkboxForCompleted = binding.checkboxForCompleted

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.completed_item_task,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = completedList[position]
        holder.textviewForCompleted.text = item.description
        holder.checkboxForCompleted.isChecked = item.checkbox

        holder.checkboxForCompleted.setOnCheckedChangeListener { _, isChecked ->
            if(!isChecked){
                listener?.secondCheckBox(item.id!!,false)
            }
        }

    }

    override fun getItemCount(): Int {
        return completedList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showInfo(taskList: List<Task>) {
        this.completedList = taskList
        notifyDataSetChanged()
    }

    private var listener:ISetDataForFirstRecycler? = null
    interface ISetDataForFirstRecycler{
        fun secondCheckBox(id: Int, checked: Boolean)
    }
    fun impInterface(myListener: ISetDataForFirstRecycler) {
        this.listener = myListener
    }

}