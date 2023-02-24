package com.example.tickytodo
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.tickytodo.SharedPreference.SharedPreference
import com.example.tickytodo.dataClasses.DataModel
import com.example.tickytodo.database.TaskViewModel
import com.example.tickytodo.databinding.ActivityMainBinding
import com.example.tickytodo.fragments.NoTaskHomeFragment
import com.example.tickytodo.fragments.OnboardFragment
import com.example.tickytodo.fragments.TaskHomeFragment


class MainActivity : AppCompatActivity() {

    private val dataModel: DataModel by viewModels()
    private lateinit var sharedPref: SharedPreference
    private lateinit var binding: ActivityMainBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mTaskViewModel = TaskViewModel(application)
        mTaskViewModel.isHomeScreenEmpty.observe(this, Observer {
            Log.d("AAA", "onCreate:$it")
            if (it){
                openFragment(NoTaskHomeFragment.newInstance(), R.id.fragment_container_view)
            }else {
                openFragment(TaskHomeFragment.newInstance(), R.id.fragment_container_view)
            }
        })

        sharedPref = SharedPreference(this)
        if (sharedPref.getValue()) {
            openFragment(NoTaskHomeFragment.newInstance(), R.id.fragment_container_view)
        } else {
            sharedPref.saveValue(true)
            openFragment(OnboardFragment.newInstance(), R.id.fragment_container_view)
        }

        dataModel.openEmptyFragment.observe(this) {
            sharedPref.saveValue(true)
            openFragment(NoTaskHomeFragment.newInstance(), R.id.fragment_container_view)
        }


    }

    private fun openFragment(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }


}


