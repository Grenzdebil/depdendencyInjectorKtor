package com.example.invoice

data class Invoice(val id: Int, val amount: Double, val currency: String) {
    fun print() {
        println("Invoice #$id: $amount $currency")
    }
}
