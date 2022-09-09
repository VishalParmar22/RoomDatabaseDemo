package com.example.roomdatabasedemo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdatabasedemo.util.Constant

@Entity(tableName = Constant.TBL_EMP)
 class Employee() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.EMP_ID)
    var id : Int = 0
    @ColumnInfo(name = Constant.EMP_FULL_NAME)
    var fullName: String = ""
    @ColumnInfo(name = Constant.EMP_ADDRESS)
    var address: String = ""
    @ColumnInfo(name = Constant.EMP_PHOTO_URL)
    var photoUrl: String = ""
    @ColumnInfo(name = Constant.EMP_CREATED_DATE)
    var createdDate: Long = System.currentTimeMillis()
    @ColumnInfo(name = Constant.EMP_MODIFIED_DATE )
    var modifiedDate: Long = System.currentTimeMillis()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        fullName = parcel.readString().toString()
        address = parcel.readString().toString()
        photoUrl = parcel.readString().toString()
        createdDate = parcel.readLong()
        modifiedDate = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fullName)
        parcel.writeString(address)
        parcel.writeString(photoUrl)
        parcel.writeLong(createdDate)
        parcel.writeLong(modifiedDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }


}

