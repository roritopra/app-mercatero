package models

import android.os.Parcel
import android.os.Parcelable

data class StoreModel(
    var id: String="",
    var name: String="",
    var distance: String="",
    var des: String="",
    var rating: Float=0f,
    var images: MutableList<String> = mutableListOf<String>(),
    var category: String="",
    var products: MutableList<ProductModel> = mutableListOf<ProductModel>()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString().toFloat(),
        parcel.createStringArrayList()?.toMutableList()!!,
        parcel.readString().toString(),
        parcel.createTypedArrayList(ProductModel.CREATOR)?.toMutableList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(distance)
        parcel.writeString(des)
        parcel.writeFloat(rating)
        parcel.writeStringList(images)
        parcel.writeString(category)
        parcel.writeTypedList(products)
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
