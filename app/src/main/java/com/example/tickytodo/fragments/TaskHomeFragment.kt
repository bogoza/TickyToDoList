package com.example.tickytodo.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodo.R
import com.example.tickytodo.adapter.CompletedTaskAdapter
import com.example.tickytodo.adapter.MyTaskAdapter
import com.example.tickytodo.dataClasses.HomeData
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentTaskHomeBinding
import kotlinx.android.synthetic.main.fragment_task_home.*
import java.nio.file.Files.delete


class TaskHomeFragment : Fragment(), MyTaskAdapter.ISetDataToUpdateFragment,
    CompletedTaskAdapter.ISetDataForFirstRecycler {

    private var _binding: FragmentTaskHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: MyTaskAdapter
    private lateinit var completedTaskAdapter: CompletedTaskAdapter
    private lateinit var taskRecycler: RecyclerView
    private lateinit var completedRecycler : RecyclerView
    private lateinit var mTaskViewModel: TaskViewModel




//    val updateTaskFragment = UpdateTaskFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        init()
        setListeners()
    }

    override fun isRecyclerEmpty(isEmpty: Boolean) {
        mTaskViewModel.isHomeScreenEmpty.postValue(isEmpty)
    }

    override fun checkBox(id: Int, checked: Boolean) {
        mTaskViewModel.updateCheckboxForItem(id,checked)
    }
    override fun secondCheckBox(id: Int, checked: Boolean) {
        mTaskViewModel.updateCheckboxForItem(id,checked)
    }


    private fun init() {
        taskAdapter = MyTaskAdapter()
        taskRecycler = binding.recyclerView
        taskRecycler.layoutManager = LinearLayoutManager(requireContext())
        taskRecycler.adapter = taskAdapter
        taskAdapter.impInterface(this)

        completedTaskAdapter = CompletedTaskAdapter()
        completedTaskAdapter.impInterface(this)
        completedRecycler = binding.completedRecycler
        completedRecycler.layoutManager = LinearLayoutManager(requireContext())
        completedRecycler.adapter = completedTaskAdapter



        mTaskViewModel.readFalseData.observe(viewLifecycleOwner, Observer { Task ->
            taskAdapter.showInfo(Task)

        } )

        mTaskViewModel.readTrueData.observe(viewLifecycleOwner, Observer { Task ->
            completedTaskAdapter.showInfo(Task)
        })




    }


    private fun setListeners() {
        binding.newTaskButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, AddTaskFragment())
                .commit()
        }
        binding.circleForCancel.setOnClickListener {
            binding.deleteTask.isVisible = false
        }

    }

    interface TaskAddedListener {
        fun onTaskAdded(task: HomeData)
    }

    companion object {
        fun newInstance() = TaskHomeFragment()
    }

    override fun setDataToUpdateFragment(user: Task) {
        Log.d("AAA", "Task: ${user.description}")
        Log.d("AAA", "Task: ${user.color}")

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, UpdateTaskFragment(user))
            .commit()

        delete_task_btn.setOnClickListener {
            mTaskViewModel.delete(user = user)
        }
    }


    override fun longClick(user: Task) {
        binding.deleteTask.isVisible = true

        delete_task_btn.setOnClickListener {
            mTaskViewModel.delete(user = user)
            delete_task.isVisible = false
        }
    }



}