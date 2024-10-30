package org.example.Utils

import org.example.*
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {

    private lateinit var emf: EntityManagerFactory


    private fun getEntityManagerFactory(namePersistenceUnit: String = "appstock"): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf;
        } else {
            emf = Persistence.createEntityManagerFactory(namePersistenceUnit)
            emf
        }
    }

    fun getEntityManager(namePersistenceUnit: String = ""): EntityManager {
        return getEntityManagerFactory(namePersistenceUnit).createEntityManager()
    }


    // Metodo para cerrar
    fun shutdown() {
        if (emf.isOpen) {
            emf.close()
        }
    }

    // Metodo para cerrar un entity en específico
    fun closeEntityManager(em: EntityManager?) {
        try {
            if (em != null && em.isOpen) {
                em.close()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }



}