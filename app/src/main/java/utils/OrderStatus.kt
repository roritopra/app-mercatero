package utils

enum class OrderStatus(val Name:String) {
    PENDING(Name = "Por aceptar"),
    PROCESSING(Name = "En preparación"),
    READY(Name = "Por entregar")
}