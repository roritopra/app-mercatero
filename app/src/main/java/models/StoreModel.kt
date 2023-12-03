package models

import android.os.Parcel
import android.os.Parcelable

data class StoreModel(
    var id: String="",
    var name: String="",
    var distance: String="",
    var des: String="",
    var rating: Float=0f,
    var img: String="",
    var category: String="",
    var products: MutableList<ProductModel> = mutableListOf<ProductModel>(),
    var orders: MutableList<OrderModel> = mutableListOf<OrderModel>()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString().toFloat(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(ProductModel.CREATOR)?.toMutableList()!!,
        parcel.createTypedArrayList(OrderModel.CREATOR)?.toMutableList()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(distance)
        parcel.writeString(des)
        parcel.writeFloat(rating)
        parcel.writeString(img)
        parcel.writeString(category)
        parcel.writeTypedList(products)
        parcel.writeTypedList(orders)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoreModel> {
        override fun createFromParcel(parcel: Parcel): StoreModel {
            return StoreModel(parcel)
        }

        override fun newArray(size: Int): Array<StoreModel?> {
            return arrayOfNulls(size)
        }
    }

}

