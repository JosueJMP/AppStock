package org.example.Repository

import jakarta.persistence.*
import org.example.Model.Usuario


class UsuarioRepository(private val emf: EntityManagerFactory) {

    fun introducirUsuario(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.persist(usuario)
            em.transaction.commit()
        } catch (e: Exception) {
            if (em.transaction.isActive) {
                em.transaction.rollback()
            }
            e.printStackTrace()
        } finally {
            em.close()
        }
    }

    fun leerUsuario(nombre: String): Usuario? {
        val em = emf.createEntityManager()
        return try {
            em.find(Usuario::class.java, nombre)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            em.close()
        }
    }

    fun readAll(): List<Usuario> {
        val em = emf.createEntityManager()
        return try {
            val query = em.createQuery("SELECT u FROM Usuario u", Usuario::class.java)
            query.resultList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        } finally {
            em.close()
        }
    }
    fun actualizarUsuario(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.merge(usuario)
            em.transaction.commit()
        } catch (e: Exception) {
            if (em.transaction.isActive) {
                em.transaction.rollback()
            }
            e.printStackTrace()
        } finally {
            em.close()
        }
    }
    fun borrarUsuario(usuario: Usuario) {
        val em = emf.createEntityManager()
        try {
            em.transaction.begin()
            val managedDpto = em.find(Usuario::class.java, usuario.nombreUsuario)
            if (managedDpto != null) {
                em.remove(managedDpto)
            }
            em.transaction.commit()
        } catch (e: Exception) {
            if (em.transaction.isActive) {
                em.transaction.rollback()
            }
            e.printStackTrace()
        } finally {
            em.close()
        }
    }

}