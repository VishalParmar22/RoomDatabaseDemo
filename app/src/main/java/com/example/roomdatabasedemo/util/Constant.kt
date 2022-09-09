package com.example.roomdatabasedemo.util

import androidx.room.ColumnInfo

object Constant {

  // database name

  const val DB_NAME = "employee_db.db"

   // Employee Table
    const val TBL_EMP = "employee"
   // column of emp table
    const val EMP_ID="id"
    const val EMP_FULL_NAME="emp_fullName"
    const val EMP_ADDRESS="emp_address"
    const val EMP_PHOTO_URL="emp_photo_url"
    const val EMP_CREATED_DATE="emp_created_date"
    const val EMP_MODIFIED_DATE="emp_modified_date"

    // Salary Table
    const val TBL_EMP_SALARY = "salary"
     // column of salary table
     const val SALARY_ID = "id"
     const val SALARY_EMP_ID = "emp_id"
     const val SALARY_EMP = "emp_salary"
    const val SALARY_EMP_MONTH = "emp_salary_month"
    const val SALARY_EMP_CREATED_DATE="emp_salary_created_date"
    const val SALARY_EMP_MODIFIED_DATE="emp_salary_modified_date"

 val NUM = "^[-+]?[0-9]+([.][0-9]+)*$".toRegex()
}