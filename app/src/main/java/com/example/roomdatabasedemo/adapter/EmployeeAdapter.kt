package com.example.roomdatabasedemo.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.example.roomdatabasedemo.databinding.ItemEmployeeBinding
import com.example.roomdatabasedemo.listener.ActionListener
import com.example.roomdatabasedemo.model.Employee
import com.example.roomdatabasedemo.screen.AddEmployeeActivity
import com.example.roomdatabasedemo.screen.ShowEmpSalaryActivity
import com.example.roomdatabasedemo.util.Constant
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EmployeeAdapter(

   private val context: Context,
   private val actionListener: ActionListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private  var employeeArrayList: ArrayList<Employee> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(context), parent, false)

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

    inner class SelectionSiteVH(binding : ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var id = binding.tvId
        var photo = binding.ivProfile
        var createdAt = binding.tvCreatedAt
        var modifyAt = binding.tvModifiedAt
        var name = binding.tvName
        var address = binding.tvAddress

        private val employee : Employee
            get() {
                return if (adapterPosition >= 0) employeeArrayList[adapterPosition] else Employee()
            }

        fun setData()
        {
            photo.load(employee.photoUrl){
                crossfade(true) // crossFade animation
                crossfade(3000) // animation in seconds ex- 3 seconds
                transformations(CircleCropTransformation(), BlurTransformation(context,3f,0.3f)
                ) // Circle Crop animation with blur reveal
            }

            id.text = employee.id.toString()
            name.text = employee.fullName
            address.text = employee.address
            createdAt.text = getTime(employee.createdDate)
            modifyAt.text = getTime(employee.modifiedDate)

        }
        init {
            binding.ivAddSalary.setOnClickListener{
                actionListener.onClick(adapterPosition,employee.id)
            }
            binding.ivEmpInfo.setOnClickListener {
                context.startActivity(Intent(context,ShowEmpSalaryActivity::class.java).putExtra(Constant.SALARY_EMP_ID,employee.id.toString()))
            }
            binding.ivDelete.setOnClickListener {
                actionListener.onDelete(employee.id)
            }
            binding.ivEdit.setOnClickListener {
               val intent = Intent(context,AddEmployeeActivity::class.java)
                intent.putExtra(Constant.EMP_ID,employee.id.toString())
                intent.putExtra(Constant.EMP_FULL_NAME,employee.fullName)
                intent.putExtra(Constant.EMP_ADDRESS,employee.address)
                intent.putExtra(Constant.EMP_PHOTO_URL,employee.photoUrl)
                intent.putExtra(Constant.EMP_CREATED_DATE,employee.createdDate)
                context.startActivity(intent)
            }
        }

    }

    fun getTime(milliseconds : Long) : String
    {
        val dateFormat : DateFormat =  SimpleDateFormat("dd MMM yyyy")
        val date = Date(milliseconds)
        return dateFormat.format(date)
    }

    override fun getItemCount(): Int = employeeArrayList.size
    fun update(employeeArrayList : ArrayList<Employee>)
    {
        this.employeeArrayList = employeeArrayList
        notifyDataSetChanged()
    }

}