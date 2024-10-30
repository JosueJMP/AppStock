package org.example.Repository

import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.*


object EntityManagerFactoryProvider {
    private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("appstock")

    fun getEntityManagerFactory(): EntityManagerFactory {
        return emf
    }

    fun close() {
        if (emf.isOpen) {
            emf.close()
        }
    }
}