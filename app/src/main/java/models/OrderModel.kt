package models

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class OrderModel(
    var orderId: String="",
    var storeId: String="",
    var userId: String="",
    var date: Date =Date(),
    var status: String="",
    var img: String="",
    var total: Double=0.0,
    var items: Int=0
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        Date(parcel.readLong()),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble().toDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(orderId)
        parcel.writeString(storeId)
        parcel.writeString(userId)
        parcel.writeLong(date.time)
        parcel.writeString(status)
        parcel.writeString(img)
        parcel.writeDouble(total)
        parcel.writeInt(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderModel> {
        override fun createFromParcel(parcel: Parcel): OrderModel {
            return OrderModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderModel?> {
            return arrayOfNulls(size)
        }
    }
}


