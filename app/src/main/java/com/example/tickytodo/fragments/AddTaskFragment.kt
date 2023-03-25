package com.example.tickytodo.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentAddTaskBinding
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.util.*


class AddTaskFragment : Fragment(),DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    private lateinit var mTaskViewModel: TaskViewModel

    private var selectedColorIndex = 5


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater,container,false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        goToTaskHomeFragment()
        listenerForCircles()
        clickListener()


    }

    //click listeners
    private fun clickListener() {
        binding.saveBtn.setOnClickListener {
            insertDataToDatabase()
        }
        binding.calendar.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
//        val datePickerFragment = DatePickerFragment()
//        datePickerFragment.show(parentFragmentManager, "datePicker")
    }

    private fun insertDataToDatabase() {
        val description = binding.editTextTask.text.toString()



        if (inputCheck(description)) {
            var task = Task(
                null,
                description = description,
                checkbox = false,
                color = 5,
                date = "04.02,2023"
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

//    class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
//        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//            val calendar: Calendar = Calendar.getInstance()
//            val year: Int = calendar.get(Calendar.YEAR)
//            val month: Int = calendar.get(Calendar.MONTH)
//            val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
//
//            return DatePickerDialog(requireContext(), this, year, month, dayOfMonth)
//        }
//
//        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//
//            Log.d("TAG", "Got the date")
//
//        }
//    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
    }
    private fun pickDate() {
        calendar.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(),this,month,year,day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month

        getDateCalendar()
        choseDate.text = "Due $savedDay $savedMonth"

    }


}

