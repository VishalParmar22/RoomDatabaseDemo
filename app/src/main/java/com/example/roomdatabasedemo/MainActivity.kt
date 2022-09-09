package com.example.roomdatabasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import com.example.roomdatabasedemo.screen.AddEmployeeSalaryActivity
import com.example.roomdatabasedemo.screen.AddEmployeeActivity
import com.example.roomdatabasedemo.screen.ShowEmployeeActivity
import com.example.roomdatabasedemo.util.Constant

class MainActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }

    private fun addListener()
    {
        binding.apply {
            btnAddEmployee.setOnClickListener(this@MainActivity)
            btnShowEmpDetail.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(p0: View?) {
       when(p0?.id)
       {
           R.id.btnAddEmployee -> startActivity(Intent(this,AddEmployeeActivity::class.java).putExtra("add","add"))
           R.id.btnShowEmpDetail -> startActivity(Intent(this,ShowEmployeeActivity::class.java))
       }
    }
}