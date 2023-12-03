package utils

enum class OrderStatus(val Name:String) {
    PENDING(Name = "Pending"),
    PROCESSING(Name = "Processing"),
    READY(Name = "Ready")
}