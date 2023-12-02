package utils

import icesi.edu.co.mercatero_app.R

object Constants {

     const val KEY_STORE="Store"
    const val KEY_DRIVER="Driver"
    const val KEY_CUSTOMER="Customer"

    val catgs= listOf("Restaurante","Mercados","Farmacia","Ferretería",
        "Mascotas","Ropa","Hogar","Licor","Papelería","Belleza")

    val catgsIcons= listOf(
        R.drawable.ic_restaurant,R.drawable.ic_market,
        R.drawable.ic_pharmacy,R.drawable.ic_tools,R.drawable.ic_pets,
        R.drawable.ic_hanger,R.drawable.ic_home,R.drawable.ic_local_drink,
        R.drawable.ic_cut,R.drawable.ic_barber)
}