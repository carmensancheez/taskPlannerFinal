package com.example.taskplanner.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskplanner.R
import com.example.taskplanner.databinding.ActivityMainBinding
import com.example.taskplanner.repository.model.entity.Task
import com.example.taskplanner.ui.activity.AddTaskFragment
import com.example.taskplanner.ui.adapter.TaskAdapter
import com.example.taskplanner.ui.adapter.TaskAdapterListener
import com.example.taskplanner.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TaskAdapterListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainActivityViewModel>()

    private val taskAdapter = TaskAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.successLiveData.observe(this, {
            updateUserInfo()
        })
        viewModel.findUserById()

        configureRecyclerView()

        viewModel.syncTaskData()

//        viewModel.errorMessageLiveData.observe(this, {
//            Snackbar.make(this@MainActivity, binding.recyclerView, it, Snackbar.LENGTH_LONG).show()
//        })


        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.addTask.setOnClickListener {
            navController.navigate(R.id.addTaskFragment)
//            findNavController().navigate(R.id.action_FirstFragment_to_addTaskFragment)

//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()

        }
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = taskAdapter

        viewModel.taskListLiveData.observe(this, {
            taskAdapter.updateTaskList(it)
        })
    }


    private fun updateUserInfo() {
        if (viewModel.user != null)
//            userName.text = viewModel.user?.name
        Log.d("Developer", "${viewModel.user?.name}")
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onClicked(task: Task) {
        TODO("Not yet implemented")
    }
}