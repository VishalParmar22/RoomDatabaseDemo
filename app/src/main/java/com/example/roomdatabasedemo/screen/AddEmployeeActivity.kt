package com.example.roomdatabasedemo.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.roomdatabasedemo.database.EmployeeDatabase
import com.example.roomdatabasedemo.databinding.ActivityAddEmployeeBinding
import com.example.roomdatabasedemo.extension.convertToLong
import com.example.roomdatabasedemo.model.Employee
import com.example.roomdatabasedemo.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEmployeeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEmployeeBinding
    private var db : EmployeeDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = EmployeeDatabase.getDatabase(applicationContext)

        if (intent.getStringExtra("add").equals("add"))
        {
            binding.btnAddEmployee.visibility = View.VISIBLE
            binding.btnEditEmployee.visibility = View.INVISIBLE
        }
        else{
            binding.btnAddEmployee.visibility = View.INVISIBLE
            binding.btnEditEmployee.visibility = View.VISIBLE
        }

        init()


    }
    private fun init()
    {
        binding.edtFullName.setText(intent.getStringExtra(Constant.EMP_FULL_NAME))
        binding.edtAddress.setText(intent.getStringExtra(Constant.EMP_ADDRESS))
        binding.edtPhoto.setText(intent.getStringExtra(Constant.EMP_PHOTO_URL))

        // add employee details
        binding.btnAddEmployee.setOnClickListener{
            if (checkValidation())
            {
                val model = Employee()

                model.fullName = binding.edtFullName.text.toString()
                model.address = binding.edtAddress.text.toString()
                model.photoUrl = binding.edtPhoto.text.toString()

                GlobalScope.launch(Dispatchers.IO) {
                   if ( db?.employeeDao()?.addEmployee(emp = model) != 0L)
                   {

                        Log.v("Inserted","Record inserted")
                   }
                }
            }

        }

        // edit employee details
        binding.btnEditEmployee.setOnClickListener{
            if (checkValidation())
            {
                val employeeModel = Employee()
                 employeeModel.id =  intent.getStringExtra(Constant.EMP_ID).toString().toInt()
                employeeModel.fullName = binding.edtFullName.text.toString()
                employeeModel.address = binding.edtAddress.text.toString()
                employeeModel.photoUrl = binding.edtPhoto.text.toString()
                employeeModel.createdDate = intent.getStringExtra(Constant.EMP_CREATED_DATE).toString().convertToLong()

                GlobalScope.launch(Dispatchers.IO) {
                   if (db?.employeeDao()?.editEmployeeDetails(employee = employeeModel)!=0)
                   {
                       Log.v("Update","Record Update")
                   }
                }
            }
        }
    }

    private fun checkValidation() : Boolean
    {
        var isValid = true

        if (binding.edtFullName.text.toString().isEmpty()  || binding.edtAddress.text.toString().isEmpty() || binding.edtPhoto.text.toString().isEmpty())
        {
            isValid = false
        }
    return isValid
    }

}