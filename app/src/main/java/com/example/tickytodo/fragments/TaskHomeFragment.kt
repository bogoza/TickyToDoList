package com.example.tickytodo.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickytodo.R
import com.example.tickytodo.adapter.MyTaskAdapter
import com.example.tickytodo.dataClasses.HomeData
import com.example.tickytodo.databinding.FragmentTaskHomeBinding


class TaskHomeFragment : Fragment() {

    private var _binding: FragmentTaskHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: MyTaskAdapter
    private lateinit var taskRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskHomeBinding.inflate(inflater)
        //code here

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setListeners()
    }

    private fun init() {
        taskAdapter = MyTaskAdapter()
        taskRecycler = binding.recyclerView
        taskRecycler.layoutManager = LinearLayoutManager(requireContext())
        taskRecycler.adapter = taskAdapter

    }

    private fun setListeners() {
        binding.newTaskButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, AddTaskFragment())
                .commit()
        }

    }

    interface TaskAddedListener {
        fun onTaskAdded(task: HomeData)
    }

    companion object {
        fun newInstance() = TaskHomeFragment()
    }



}