package viewmodels

import androidx.lifecycle.ViewModel
import models.ProductModel
import models.StoreModel


class SharedViewModel : ViewModel() {

    var productsList = mutableListOf<ProductModel>()
    lateinit var store: StoreModel
    var total=0.0
    var discount=0
    var totalCounts=0
    var shipment=5




}