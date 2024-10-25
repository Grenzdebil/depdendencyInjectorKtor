package com.example.invoice

class InvoiceController(private val invoiceService: InvoiceService) {
    fun getInvoice(id: Int): Invoice {
        return invoiceService.getInvoice(id)
    }
}
