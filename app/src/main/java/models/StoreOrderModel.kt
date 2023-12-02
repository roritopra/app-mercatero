package models

import adapters.StoresAdapter

data class StoreOrderModel(
    var orderId: String="",
    var date: String="",
    var status: String="",
    var img: String="",
    var total: Double=0.0,
    var items: Int=0
)


