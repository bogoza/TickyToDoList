package com.example.tickytodo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.tickytodo.dataClasses.DataModel
import com.example.tickytodo.dataStore.DataStore
import com.example.tickytodo.viewmodel.TaskViewModel
import com.example.tickytodo.databinding.ActivityMainBinding
import com.example.tickytodo.fragments.NoTaskHomeFragment
import com.example.tickytodo.fragments.OnboardFragment
import com.example.tickytodo.fragments.TaskHomeFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val dataModel: DataModel by viewModels()
    private lateinit var dataStore: DataStore
    private lateinit var binding: ActivityMainBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mTaskViewModel = TaskViewModel(application)

        dataStoreOnboard() //checking if launching app first time
        finishOnboard()//going to another fragment from onboard

    }

    private fun finishOnboard() {
        dataModel.openEmptyFragment.observe(this) {
            lifecycleScope.launch {
                dataStore.saveValue(true)
                openFragment(NoTaskHomeFragment.newInstance(), R.id.fragment_container_view)
            }

        }
    }

    private fun dataStoreOnboard() {
        dataStore = DataStore(this)
        lifecycleScope.launch {
            val value = dataStore.valueFlow.first()
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (value) {
                mTaskViewModel.readAllData.observe(this@MainActivity,Observer{
                       if(it.isEmpty() && currentFragment !is NoTaskHomeFragment){
                           openFragment(NoTaskHomeFragment.newInstance(), R.id.fragment_container_view)
                        }else if(it.isNotEmpty() && currentFragment !is TaskHomeFragment){
                           openFragment(TaskHomeFragment.newInstance(),R.id.fragment_container_view)
                       }
                    })
            } else {
                dataStore.saveValue(true)
                openFragment(OnboardFragment.newInstance(), R.id.fragment_container_view)
            }
        }
    }




    private fun openFragment(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, f)
            .commit()
    }


}


