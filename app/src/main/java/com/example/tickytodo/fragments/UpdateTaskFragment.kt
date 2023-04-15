package com.example.tickytodo.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tickytodo.R
import com.example.tickytodo.database.Task
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.FragmentUpdateTaskBinding
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.util.*

class UpdateTaskFragment(private val currentTask: Task) : Fragment(),DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentUpdateTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var mTaskViewModel: TaskViewModel
    private var selectedColorIndex = 0


    private var day = 0
    private var month = 0
    private var year = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0

    private var dateForDb = currentTask.date
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

        showCurrentTask()
        listenerForCircles()
        currentCircleColor()
        clickListener()
        hideCalendar()
        pickDate()


    }
    private fun clickListener() {
        binding.updateBtn.setOnClickListener {
            updateItem()
        }
        binding.backArrConstraint.setOnClickListener {
            goHome()
        }
        binding.cancelContainer.setOnClickListener {
            goHome()
        }
        binding.xOnDate.setOnClickListener {
            dateForDb = ""
            binding.calendar.isVisible = true
            binding.dateOn.isVisible = false
        }

    }
    private fun currentCircleColor() {
        when (selectedColorIndex) {
            0 -> {
                binding.redCircleBorder.visibility = View.VISIBLE
            }
            1 -> {
                binding.orangeCircleBorder.visibility = View.VISIBLE
            }
            2 -> {
                binding.yellowCircleBorder.visibility = View.VISIBLE
            }
            3 -> {
                binding.greenCircleBorder.visibility = View.VISIBLE
            }
            4 -> {
                binding.blueCircleBorder.visibility = View.VISIBLE
            }
            5 -> {
                binding.darkBlueCircleBorder.visibility = View.VISIBLE
            }
            6 -> {
                binding.purpleCircleBorder.visibility = View.VISIBLE
            }
            7 -> {
                binding.lightPurpleCircleBorder.visibility = View.VISIBLE
            }
            8 -> {
                binding.pinkCircleBorder.visibility = View.VISIBLE
            }
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
                date = dateForDb
            )
            mTaskViewModel.update(updateUser)
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

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }
    private fun pickDate(){
        binding.calendar.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
        binding.updatedChooseDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

private fun hideCalendar(){
   if (currentTask.date != ""){
       binding.calendar.isVisible = false
       binding.dateOn.isVisible = true
       binding.updatedChooseDate.text = currentTask.date

   }else{
       binding.dateOn.isVisible = false
       binding.calendar.isVisible = true
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
        binding.updatedChooseDate.text = "Due $savedDay $yearInString."
        dateForDb = "Due $savedDay $yearInString."
        binding.calendar.isVisible = false
        binding.dateOn.isVisible = true
    }

}