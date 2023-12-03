package utils

import icesi.edu.co.mercatero_app.R

object Constants {

    const val KEY_STORE="Tienda"
    const val KEY_DRIVER="Repartidor"
    const val KEY_CONSUMER="Consumidor"
    const val KEY_REGISTRATION="Registration"
    const val KEY_ORDER="Order"
    const val KEY_IMAGES="Images"

    const val COLLECTION_USERS="Users"
    const val COLLECTION_ORDERS="Orders"
    const val COLLECTION_STORES="Stores"

    val dateFormat = "MMM dd, yyyy"
    const val ITEM_VERTICAL=0
    const val ITEM_HORIZONTAL=1
    const val KEY_TAB_POSITION="tabPosition"

    val catgs= listOf("Restaurante","Mercados","Farmacia","Ferretería",
        "Mascotas","Ropa","Hogar","Licor","Papelería","Belleza")

    val catgsIcons= listOf(
        R.drawable.ic_restaurant,R.drawable.ic_market,
        R.drawable.ic_pharmacy,R.drawable.ic_tools,R.drawable.ic_pets,
        R.drawable.ic_hanger,R.drawable.ic_home,R.drawable.ic_local_drink,
        R.drawable.ic_cut,R.drawable.ic_barber)
}