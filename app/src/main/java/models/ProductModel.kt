package models

import android.os.Parcel
import android.os.Parcelable

data class ProductModel(
    var id: String="",
    var name: String="",
    var storeName: String="",
    var storeId: String="",
    var rating: Float=0f,
    var description: String="",
    var price: Double=0.0,
    var counts:Int=0,
    var imgs: MutableList<String> = mutableListOf<String>(),
):Parcelable {
    constructor(parcel: Parcel) : this(){
        parcel.readString().toString()
        parcel.readString().toString()
        parcel.readString().toString()
        parcel.readString().toString()
        parcel.readString().toString().toFloat()
        parcel.readString().toString()
        parcel.readString().toString()
        parcel.readString().toString().toDouble()
        parcel.readInt()
        parcel.createStringArrayList()?.toMutableList()!!

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(storeName)
        parcel.writeString(storeId)
        parcel.writeFloat(rating)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeInt(counts)
        parcel.writeStringList(imgs)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}
