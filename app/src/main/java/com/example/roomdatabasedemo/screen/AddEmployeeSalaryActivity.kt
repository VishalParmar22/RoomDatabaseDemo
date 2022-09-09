package com.example.roomdatabasedemo.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabasedemo.database.EmployeeDatabase
import com.example.roomdatabasedemo.databinding.ActivityAddEmpSalaryBinding
import com.example.roomdatabasedemo.extension.convertToLong
import com.example.roomdatabasedemo.model.Salary
import com.example.roomdatabasedemo.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEmployeeSalaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmpSalaryBinding
    private var db : EmployeeDatabase? = null

    var salary : Long = 0
    var month : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmpSalaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = EmployeeDatabase.getDatabase(applicationContext)
        binding.edtEmpId.setText(intent.getStringExtra(Constant.EMP_ID).toString())
        init()
    }
    private fun init()
    {

        binding.btnAddSalary.setOnClickListener{
            if (checkValidation())
            {
                val salary = Salary()
                salary.empId = intent.getStringExtra(Constant.EMP_ID).toString().toInt()
                salary.salary = binding.edtEmpSalary.text.toString().convertToLong()
                salary.month = binding.edtMonth.text.toString()

                GlobalScope.launch(Dispatchers.IO) {
                      db?.employeeDao()?.addSalaryEmployee(salary = salary)
                }
            }
        }
    }

    private fun checkValidation() : Boolean
    {
        var isValid = true
        if (binding.edtEmpSalary.text.toString().isEmpty() || binding.edtMonth.text.toString().isEmpty())
        {
            isValid = false
        }
         return isValid
    }
}