package com.example.roomdatabasedemo.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.adapter.EmployeeAdapter
import com.example.roomdatabasedemo.database.EmployeeDatabase
import com.example.roomdatabasedemo.databinding.ActivityShowEmployeeBinding
import com.example.roomdatabasedemo.listener.ActionListener
import com.example.roomdatabasedemo.model.Employee
import com.example.roomdatabasedemo.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowEmployeeActivity : AppCompatActivity(),ActionListener{
    private lateinit var binding: ActivityShowEmployeeBinding
    private lateinit var employeeAdapter : EmployeeAdapter
    private var db : EmployeeDatabase? = null
    private var list : List<Employee>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = EmployeeDatabase.getDatabase(applicationContext)

        init()
    }
    private fun init()
    {
        binding.rvEmployee.apply {
            layoutManager = LinearLayoutManager(this@ShowEmployeeActivity, RecyclerView.VERTICAL,false)
            employeeAdapter = EmployeeAdapter(this@ShowEmployeeActivity,this@ShowEmployeeActivity)
            adapter = employeeAdapter
        }


        GlobalScope.launch(Dispatchers.IO ) {
             list = db?.employeeDao()?.getAllEmployeeDetail()
           runOnUiThread {
               employeeAdapter.update(list as ArrayList<Employee>)
           }
        }
    }

    override fun onClick(position: Int, extraData: Any) {
        super.onClick(position, extraData)
        startActivity(Intent(this,AddEmployeeSalaryActivity::class.java).putExtra(Constant.EMP_ID,extraData.toString()))
    }

    override fun onDelete(position: Int) {
        super.onDelete(position)

        GlobalScope.launch(Dispatchers.IO){
            db?.employeeDao()?.removeEmployee(position)
        }
    }

}