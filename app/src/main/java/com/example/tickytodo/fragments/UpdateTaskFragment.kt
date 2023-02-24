package com.example.tickytodo.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentUpdateTaskBinding

class UpdateTaskFragment(private val currentTask: Task) : Fragment() {

    private var _binding: FragmentUpdateTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var mTaskViewModel: TaskViewModel
    private var selectedColorIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun inputCheck(description: String): Boolean {
        return !(TextUtils.isEmpty(description))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
//        binding.editTextTask.setText(currentData.description)
        backButtons()
        showCurrentTask()
        listenerForCircles()
        currentCircleColor()
        binding.updateBtn.setOnClickListener {
            updateItem()
        }


    }

    private fun currentCircleColor() {
        when (selectedColorIndex) {
            0 -> {binding.redCircleBorder.visibility = View.VISIBLE}
            1 -> {binding.orangeCircleBorder.visibility = View.VISIBLE}
            2 -> {binding.yellowCircleBorder.visibility = View.VISIBLE}
            3 -> {binding.greenCircleBorder.visibility = View.VISIBLE}
            4 -> {binding.blueCircleBorder.visibility = View.VISIBLE}
            5 -> {binding.darkBlueCircleBorder.visibility = View.VISIBLE}
            6 -> {binding.purpleCircleBorder.visibility = View.VISIBLE}
            7 -> {binding.lightPurpleCircleBorder.visibility = View.VISIBLE}
            8 -> {binding.pinkCircleBorder.visibility = View.VISIBLE}
        }
    }

    private fun showCurrentTask() {
        binding.editTextTask.setText(currentTask.description)
        selectedColorIndex = currentTask.color

    }

    private fun updateItem() {
        val desc = binding.editTextTask.text.toString()
        if (inputCheck(desc)) {
            val updateUser = Task(
                id = currentTask.id,
                description = desc,
                checkbox = currentTask.checkbox,
                color = selectedColorIndex,
                date = currentTask.date
            )
            mTaskViewModel.update(updateUser)
            goHome()
        }

    }

    private fun backButtons() {
        binding.backArrConstraint.setOnClickListener {
            goHome()
        }
        binding.cancelContainer.setOnClickListener {
            goHome()
        }
    }

    private fun goHome() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, TaskHomeFragment())
            .commit()
    }

    private fun listenerForCircles() {
        binding.redCircleBtn.setOnClickListener {
            selectedColorIndex = 0
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
            selectedColorIndex = 1
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
            selectedColorIndex = 2
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
            selectedColorIndex = 3
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
            selectedColorIndex = 4
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
            selectedColorIndex = 5
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
            selectedColorIndex = 6
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
            selectedColorIndex = 7
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
            selectedColorIndex = 8
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

}