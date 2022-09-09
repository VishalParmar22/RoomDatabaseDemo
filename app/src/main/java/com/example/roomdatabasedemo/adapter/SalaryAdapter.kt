package com.example.roomdatabasedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.databinding.ItemSalaryEmployeeBinding
import com.example.roomdatabasedemo.listener.ActionListener
import com.example.roomdatabasedemo.model.EmployeeWithSalary
import com.example.roomdatabasedemo.model.Salary
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SalaryAdapter(
    private val context: Context,
    private val actionListener: ActionListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private  var salaryArrayList: ArrayList<Salary> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemSalaryEmployeeBinding.inflate(LayoutInflater.from(context), parent, false)

        return SelectionSiteVH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.adapterPosition >= 0)
        {
            when(holder)
            {
                is SelectionSiteVH -> holder.setData()
            }
        }
    }

    inner class SelectionSiteVH(binding : ItemSalaryEmployeeBinding) : RecyclerView.ViewHolder(binding.root)
    {

        var empsalary = binding.tvSalaryEmp
        private var createdAt = binding.tvCreatedAt
      private var modifyAt = binding.tvModifiedAt
      private var month = binding.tvSalaryEmpMonth


        private val salary : Salary
            get() {
                return if (adapterPosition >= 0) salaryArrayList[adapterPosition] else Salary()
            }

        fun setData()
        {
            empsalary.text = salary.salary.toString()
            createdAt.text = getTime(salary.createdDate)
            modifyAt.text = getTime(salary.modifiedDate)
            month.text = salary.month
        }


    }

    fun getTime(milliseconds : Long) : String
    {
        val dateFormat : DateFormat =  SimpleDateFormat("dd MMM yyyy")
        val date = Date(milliseconds)
        return dateFormat.format(date)
    }

    override fun getItemCount(): Int = salaryArrayList.size
    fun update(salaryArrayList: ArrayList<Salary>)
    {
        this.salaryArrayList = salaryArrayList
        notifyDataSetChanged()
    }

}