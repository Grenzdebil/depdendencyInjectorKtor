package com.example.di

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

class DIContainer {
    private val registry = mutableMapOf<KClass<*>, Scope>()
    private val instances = mutableMapOf<KClass<*>, Any>()

    /**
     * Transient scope creates a new instance every time the service is resolved.
     * Singleton scope creates a single instance and caches it for future resolutions.
     */
    enum class Scope { SINGLETON, TRANSIENT }

    fun <T : Any> register(service: KClass<T>, scope: Scope = Scope.SINGLETON) {
        registry[service] = scope
        if (scope == Scope.SINGLETON) {
            instances[service] = createInstance(service)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> resolve(service: KClass<T>): T {
        return when (registry[service]) {
            Scope.SINGLETON -> instances[service] as? T ?: throw DependencyResolutionException("Service not registered: ${service.simpleName}")
            Scope.TRANSIENT -> createInstance(service)
            null -> throw DependencyResolutionException("Service not registered: ${service.simpleName}")
        }
    }

    private fun <T : Any> createInstance(clazz: KClass<T>): T {
        val constructor = clazz.primaryConstructor
            ?: throw DependencyResolutionException("No primary constructor found for ${clazz.simpleName}")

        val parameters = constructor.parameters.map { parameter ->
            val paramClass = parameter.type.classifier as? KClass<*>
                ?: throw DependencyResolutionException("Could not resolve parameter type for ${parameter.name}")
            resolve(paramClass)
        }

        return constructor.call(*parameters.toTypedArray())
    }

    fun clear() {
        instances.clear()
        registry.clear()
    }
}

class DependencyResolutionException(message: String) : Exception(message)
