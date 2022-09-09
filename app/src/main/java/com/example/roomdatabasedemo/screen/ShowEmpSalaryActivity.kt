package com.example.roomdatabasedemo.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.adapter.SalaryAdapter
import com.example.roomdatabasedemo.database.EmployeeDatabase
import com.example.roomdatabasedemo.databinding.ActivityShowEmpSalaryBinding
import com.example.roomdatabasedemo.listener.ActionListener
import com.example.roomdatabasedemo.model.Salary
import com.example.roomdatabasedemo.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ShowEmpSalaryActivity : AppCompatActivity(),ActionListener{

    private lateinit var binding: ActivityShowEmpSalaryBinding
    private var db : EmployeeDatabase? = null
    private var salaryAdapter : SalaryAdapter?= null
    private var salaryArrayList  : ArrayList<Salary> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowEmpSalaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.commLayout.ivEmpInfo.visibility = View.INVISIBLE
        binding.commLayout.ivAddSalary.visibility = View.INVISIBLE
        db = EmployeeDatabase.getDatabase(applicationContext)
        init()
    }
    private fun init()
    {
        binding.rvEmployeeSalary.apply {
            layoutManager = LinearLayoutManager(this@ShowEmpSalaryActivity, RecyclerView.VERTICAL,false)
            salaryAdapter = SalaryAdapter(this@ShowEmpSalaryActivity,this@ShowEmpSalaryActivity)
            adapter = salaryAdapter
        }

        val id = intent.getStringExtra(Constant.SALARY_EMP_ID).toString()
        GlobalScope.launch(Dispatchers.IO) {
            val list = db?.employeeDao()?.getAllEmployeeSalary(id.toInt())
            salaryAdapter?.update(list as ArrayList<Salary>)
        }

    }

}