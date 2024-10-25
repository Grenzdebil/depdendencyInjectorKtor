package com.example

import com.example.di.DIContainer
import com.example.invoice.InvoiceController
import com.example.invoice.InvoiceService
import com.example.plugins.*
import com.example.services.GreetingService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val diContainer = DIContainer()

    diContainer.register(GreetingService::class)
    diContainer.register(InvoiceService::class)
    diContainer.register(InvoiceController::class)

    configureRouting(diContainer)
}
