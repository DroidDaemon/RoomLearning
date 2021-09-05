package com.droiddaemon.roomdatabaselearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.roomdemo.MyRecyclerViewAdapter
import com.droiddaemon.roomdatabaselearning.data.Student
import com.droiddaemon.roomdatabaselearning.data.StudentDataBase
import com.droiddaemon.roomdatabaselearning.data.StudentRepository
import com.droiddaemon.roomdatabaselearning.databinding.ActivityMainBinding
import com.droiddaemon.roomdatabaselearning.viewModel.StudentViewModel
import com.droiddaemon.roomdatabaselearning.viewModel.StudentViewmodelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var adapter: MyRecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = StudentDataBase.getInstance(application).studentDAO
        val repository = StudentRepository(dao)
        val factory = StudentViewmodelFactory(repository)
        studentViewModel = ViewModelProvider(this,factory).get(StudentViewModel::class.java)
        binding.myViewModel = studentViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        studentViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({selectedItem:Student->listItemClicked(selectedItem)})
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        studentViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Student){
        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        studentViewModel.initUpdateAndDelete(subscriber)
    }
}




//Problem Description
//
//You are given two linked lists, A and B representing two non-negative numbers.
//
//The digits are stored in reverse order and each of their nodes contain a single digit.
//
//Add the two numbers and return it as a linked list.