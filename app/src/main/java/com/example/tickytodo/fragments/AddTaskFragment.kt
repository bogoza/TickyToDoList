package com.example.tickytodo.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentAddTaskBinding
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.util.*


class AddTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private var day = 0
    private var month = 0
    private var year = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0

    private var dateForDb = ""

    private lateinit var mTaskViewModel: TaskViewModel

    private var selectedColorIndex = 5


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        goToTaskHomeFragment()
        listenerForCircles()
        clickListener()
        pickDate()

    }

    //click listeners
    private fun clickListener() {
        binding.saveBtn.setOnClickListener {
            insertDataToDatabase()
        }
        binding.xOnDate.setOnClickListener {
            dateForDb = ""
            binding.calendar.isVisible = true
            binding.dateOn.isVisible = false
        }
    }


    private fun insertDataToDatabase() {
        val description = binding.editTextTask.text.toString()



        if (inputCheck(description)) {
            val task = Task(
                null,
                description = description,
                checkbox = false,
                color = selectedColorIndex,
                date = dateForDb
            )
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
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

    private var listener: TaskHomeFragment.TaskAddedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TaskHomeFragment.TaskAddedListener) {
            listener = context
        }
    }

    private fun inputCheck(description: String): Boolean {
        return !(TextUtils.isEmpty(description))
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }


    private fun pickDate() {
        calendar.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        binding.dateOn.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        var yearInString = ""

        when (savedMonth) {
            0 -> yearInString = "Jan"
            1 -> yearInString = "Feb"
            2 -> yearInString = "Mar"
            3 -> yearInString = "Apr"
            4 -> yearInString = "May"
            5 -> yearInString = "Jun"
            6 -> yearInString = "Jul"
            7 -> yearInString = "Aug"
            8 -> yearInString = "Sep"
            9 -> yearInString = "Oct"
            10 -> yearInString = "Nov"
            11 -> yearInString = "Dec"
        }

        getDateCalendar()
        choseDate.text = "Due $savedDay $yearInString."
        dateForDb = "Due $savedDay $yearInString."
        binding.calendar.isVisible = false
        binding.dateOn.isVisible = true
    }


}

