package com.example.tickytodo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tickytodo.dataClasses.DataModel
import com.example.tickytodo.R
import com.example.tickytodo.adapter.MyTaskAdapter
import com.example.tickytodo.database.Task
import com.example.tickytodo.databinding.FragmentNoTaskHomeBinding


class NoTaskHomeFragment : Fragment() {

    private var _binding: FragmentNoTaskHomeBinding? = null
    private val binding get() = _binding!!
    private val dataModel: DataModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoTaskHomeBinding.inflate(inflater,container,false)
        //code here

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noTaskButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view,AddTaskFragment())
                .commit()
        }

    }

    companion object{
        fun newInstance() = NoTaskHomeFragment()
    }



}