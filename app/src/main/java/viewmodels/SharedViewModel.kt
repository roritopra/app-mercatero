package viewmodels

import androidx.lifecycle.ViewModel
import models.ProductModel


class SharedViewModel : ViewModel() {

    var productsList = mutableListOf<ProductModel>()
    var total=0.0
    var discount=0
    var totalCounts=0
    var shipment=0



}