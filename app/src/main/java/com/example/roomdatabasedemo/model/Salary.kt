package com.example.roomdatabasedemo.model
import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.example.roomdatabasedemo.util.Constant

@Entity(tableName = Constant.TBL_EMP_SALARY,foreignKeys = [
    ForeignKey(
        entity = Employee::class,
        parentColumns = [Constant.EMP_ID],
        childColumns = [Constant.SALARY_EMP_ID])])
class Salary() : Parcelable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.SALARY_ID)
    var id : Int = 0
    @ColumnInfo(name = Constant.SALARY_EMP_ID)
    var empId: Int = 1
    @ColumnInfo(name = Constant.SALARY_EMP)
    var salary: Long = 0
    @ColumnInfo(name = Constant.SALARY_EMP_MONTH)
    var month: String = ""
    @ColumnInfo(defaultValue = Constant.SALARY_EMP_CREATED_DATE)
    var createdDate: Long = System.currentTimeMillis()
    @ColumnInfo(defaultValue = Constant.SALARY_EMP_MODIFIED_DATE )
    var modifiedDate: Long = System.currentTimeMillis()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        empId = parcel.readInt()
        salary = parcel.readLong()
        month = parcel.readString().toString()
        createdDate = parcel.readLong()
        modifiedDate = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(empId)
        parcel.writeLong(salary)
        parcel.writeString(month)
        parcel.writeLong(createdDate)
        parcel.writeLong(modifiedDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Salary> {
        override fun createFromParcel(parcel: Parcel): Salary {
            return Salary(parcel)
        }

        override fun newArray(size: Int): Array<Salary?> {
            return arrayOfNulls(size)
        }
    }
}