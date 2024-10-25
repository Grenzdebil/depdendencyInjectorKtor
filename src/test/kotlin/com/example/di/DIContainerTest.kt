package com.example.di

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class DIContainerTest {

    class ServiceA
    class ServiceB(val serviceA: ServiceA)
    class ServiceC(val serviceB: ServiceB)

    @Test
    fun `should resolve simple singleton service`() {
        val container = DIContainer()
        container.register(ServiceA::class, DIContainer.Scope.SINGLETON)

        val instanceA1 = container.resolve(ServiceA::class)
        val instanceA2 = container.resolve(ServiceA::class)

        assertSame(instanceA1, instanceA2, "Singleton instances should be the same")
    }

    @Test
    fun `should resolve dependent services`() {
        val container = DIContainer()
        container.register(ServiceA::class)
        container.register(ServiceB::class)
        container.register(ServiceC::class)

        val instanceC = container.resolve(ServiceC::class)

        assertNotNull(instanceC, "ServiceC should be resolved")
        assertNotNull(instanceC.serviceB, "ServiceB should be injected into ServiceC")
        assertNotNull(instanceC.serviceB.serviceA, "ServiceA should be injected into ServiceB")

        // Check that dependencies are singletons within this test
        assertSame(instanceC.serviceB.serviceA, container.resolve(ServiceA::class))
        assertSame(instanceC.serviceB, container.resolve(ServiceB::class))
    }

    @Test
    fun `should create new instance for transient service`() {
        val container = DIContainer()
        container.register(ServiceA::class, DIContainer.Scope.TRANSIENT)

        val instanceA1 = container.resolve(ServiceA::class)
        val instanceA2 = container.resolve(ServiceA::class)

        assertNotSame(instanceA1, instanceA2, "Transient instances should be different")
    }
}
