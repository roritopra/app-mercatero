package models

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    var id: String="",
    var name: String="",
    var surName: String="",
    var email: String="",
    var phone: String="",
    var des: String="",
    var userType: String="",
    var img: String="",
    var orders: MutableList<OrderModel> = mutableListOf<OrderModel>()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(OrderModel.CREATOR)?.toMutableList()!!,
    ) {
    }


    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(surName)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(des)
        parcel.writeString(userType)
        parcel.writeString(img)
        parcel.writeTypedList(orders)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
