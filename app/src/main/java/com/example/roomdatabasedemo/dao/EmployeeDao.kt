package com.example.roomdatabasedemo.dao

import androidx.room.*
import com.example.roomdatabasedemo.model.Employee
import com.example.roomdatabasedemo.model.Salary
import com.example.roomdatabasedemo.util.Constant

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployee(emp: Employee) : Long

    // add employee
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSalaryEmployee(salary : Salary)

    // for get all information about employee
    @Query("select *from ${Constant.TBL_EMP}")
    fun getAllEmployeeDetail() : List<Employee>

    // for salary display
    @Query("select *from ${Constant.TBL_EMP_SALARY} where ${Constant.SALARY_EMP_ID}=:id")
    fun getAllEmployeeSalary(id:Int) : List<Salary>

    @Query("delete from ${Constant.TBL_EMP} where ${Constant.EMP_ID} =:id")
    fun removeEmployee(id:Int)

    @Update
    fun editEmployeeDetails(employee : Employee) : Int

    // modify employee details
   /* @Query("update ${Constant.TBL_EMP} set ${Constant.EMP_FULL_NAME} = :fullName,${Constant.EMP_ADDRESS}=:address,${Constant.EMP_PHOTO_URL}=:photoUrl,${Constant.EMP_CREATED_DATE}=:createdDate,${Constant.EMP_MODIFIED_DATE}=:modifiedAt where ${Constant.EMP_ID}=:id")
    suspend fun editEmployeeDetails(id: Int, fullName: String, address: String, photoUrl: String, createdDate: Long, modifiedAt: Long)
   */

    /*  @Query("select ${Constant.TBL_EMP}.*, ${Constant.TBL_EMP_SALARY}.* from ${Constant.TBL_EMP},${Constant.TBL_EMP_SALARY} where ${Constant.TBL_EMP_SALARY}.${Constant.SALARY_EMP_ID} = :id")
      fun getAllEmployeeSalary(id :  Int) : List<EmployeeWithSalary>*/

}
