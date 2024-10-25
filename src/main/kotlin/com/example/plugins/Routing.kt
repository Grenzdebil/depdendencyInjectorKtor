package com.example.plugins

import com.example.di.DIContainer
import com.example.invoice.InvoiceController
import com.example.services.GreetingService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(diContainer: DIContainer) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/greet/{name}") {
            val name = call.parameters["name"] ?: "World"
            val greetingService = diContainer.resolve(GreetingService::class)
            call.respondText(greetingService.greet(name))
        }

        get("/invoice/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respondText("Invalid ID")
            } else {
                val invoiceController = diContainer.resolve(InvoiceController::class)
                val invoice = invoiceController.getInvoice(id)
                call.respondText(invoice.toString())
            }
        }
    }
}
