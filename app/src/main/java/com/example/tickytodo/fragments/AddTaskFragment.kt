package com.example.tickytodo.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tickytodo.R
import com.example.tickytodo.dataClasses.HomeData
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentAddTaskBinding
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.item_task_layout.*


class AddTaskFragment : Fragment(){

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        goToTaskHomeFragment()
        listenerForCircles()
        binding.saveBtn.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val description = binding.editTextTask.text.toString()


        if(inputCheck(description)){
            val task = Task(0, description = description, checkbox = false, color = 0, date = "04.02,2023")
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, TaskHomeFragment())
                .commit()
        }
    }

    //this function works for every go task Home Fragment button like
    private fun goToTaskHomeFragment() {
        binding.backArrConstraint.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, TaskHomeFragment())
                .commit()
        }
        binding.cancelContainer.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, TaskHomeFragment())
                .commit()
        }
        binding.xAsCancel.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, TaskHomeFragment())
                .commit()
        }


    }

    private fun listenerForCircles() {
        binding.redCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.VISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.orangeCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.VISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.yellowCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.VISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.greenCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.VISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.blueCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.VISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.blueCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.VISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.darkBlueCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.VISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.purpleCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.VISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.lightPurpleCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.VISIBLE
            binding.pinkCircleBorder.visibility = View.INVISIBLE
        }
        binding.pinkCircleBtn.setOnClickListener {
            binding.redCircleBorder.visibility = View.INVISIBLE
            binding.orangeCircleBorder.visibility = View.INVISIBLE
            binding.yellowCircleBorder.visibility = View.INVISIBLE
            binding.greenCircleBorder.visibility = View.INVISIBLE
            binding.blueCircleBorder.visibility = View.INVISIBLE
            binding.darkBlueCircleBorder.visibility = View.INVISIBLE
            binding.purpleCircleBorder.visibility = View.INVISIBLE
            binding.lightPurpleCircleBorder.visibility = View.INVISIBLE
            binding.pinkCircleBorder.visibility = View.VISIBLE
        }
    }

    private var listener: TaskHomeFragment.TaskAddedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TaskHomeFragment.TaskAddedListener) {
            listener = context
        }
    }

    private fun inputCheck(description: String):Boolean{
        return!(TextUtils.isEmpty(description))
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



}



