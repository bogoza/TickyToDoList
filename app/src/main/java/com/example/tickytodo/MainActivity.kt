package com.example.tickytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.tickytodo.dataClasses.DataModel
import com.example.tickytodo.fragments.NoTaskHomeFragment
import com.example.tickytodo.fragments.OnboardFragment
import com.example.tickytodo.SharedPreference.SharedPreference
import com.example.tickytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val dataModel: DataModel by viewModels()
    private lateinit var sharedPref: SharedPreference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = SharedPreference(this)
        if(sharedPref.getValue()){
            openFragment(NoTaskHomeFragment.newInstance(),R.id.fragment_container_view)
        }else {
            sharedPref.saveValue(true)
            openFragment(OnboardFragment.newInstance(),R.id.fragment_container_view)
        }

        dataModel.openEmptyFragment.observe(this){
            sharedPref.saveValue(true)
            openFragment(NoTaskHomeFragment.newInstance(),R.id.fragment_container_view)
        }


    }

    private fun openFragment(f: Fragment, idHolder: Int){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder,f)
            .commit()
    }



}


