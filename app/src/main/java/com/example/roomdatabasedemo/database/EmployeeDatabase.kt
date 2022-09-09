package com.example.roomdatabasedemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabasedemo.dao.EmployeeDao
import com.example.roomdatabasedemo.model.Employee
import com.example.roomdatabasedemo.model.Salary
import com.example.roomdatabasedemo.util.Constant

@Database(entities = [Employee::class,Salary::class],version = 1,exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun employeeDao() : EmployeeDao

    companion object{
        @Volatile
        private var INSTANCE :  EmployeeDatabase? = null

        fun getDatabase(context: Context) : EmployeeDatabase{
            val tempInstance = INSTANCE

            if (tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(context.applicationContext, EmployeeDatabase::class.java, Constant.DB_NAME).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}