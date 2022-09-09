package com.example.roomdatabasedemo.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomdatabasedemo.util.Constant

class EmployeeWithSalary {

    @Embedded val  employee : Employee = Employee()
    @Relation(parentColumn = Constant.EMP_ID,
            entityColumn = Constant.SALARY_EMP_ID
    )
    val empSalary : ArrayList<Salary> = ArrayList()
}