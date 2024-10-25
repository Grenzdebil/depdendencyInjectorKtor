package com.example.invoice

class InvoiceService {
    fun getInvoice(id: Int): Invoice = Invoice(id, 1337.0, "USD")
}
