package org.example.Repository

import jakarta.persistence.*
import org.example.Model.*
import org.example.Utils.HibernateUtils

class ProductoRepository {

    fun create(producto : Producto) {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            e.printStackTrace()
        } finally {
            HibernateUtils.closeEntityManager(em)
        }
    }

    fun read(id: String) : Producto? {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        return try {
            em.find(Producto::class.java, id)
        } finally {
            HibernateUtils.closeEntityManager(em)
        }
    }

    fun readProveedorProducto(id: String): Proveedor? {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        return try{
            val producto = em.find(Producto::class.java, id)
            producto.proveedor
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            em.close()
        }
    }

    fun readProductoSinStock(): List<Producto> {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        return try {
            val query = em.createQuery("SELECT p FROM Producto p WHERE p.stock = 0", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        } finally {
            em.close()
        }
    }

    fun readProductoConStock(): List<Producto> {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        return try {
            val query = em.createQuery("SELECT p FROM Producto p WHERE p.stock > 0", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        } finally {
            em.close()
        }
    }

    fun readAll(): List<Producto> {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        return try {
            val query = em.createQuery("SELECT p FROM Producto p", Producto::class.java)
            query.resultList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        } finally {
            em.close()
        }
    }

    fun updateProductoNombre(id: String, nuevoNombre: String) {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        try {
            val producto = read(id)
            if (producto != null) {
                producto.nombre = nuevoNombre
                em.transaction.begin()
                em.merge(producto)
                em.transaction.commit()
            }
        } catch (e: Exception) {
            if (em.transaction.isActive) {
                em.transaction.rollback()
            }
            e.printStackTrace()
        } finally {
            em.close()
        }
    }

    fun updateProductoStock(id: String, nuevoStock: Int) {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        try {
            val producto = read(id)
            if (producto != null) {
                producto.stock = nuevoStock
                em.transaction.begin()
                em.merge(producto)
                em.transaction.commit()
            }
        } catch (e: Exception) {
            if (em.transaction.isActive) {
                em.transaction.rollback()
            }
            e.printStackTrace()
        } finally {
            em.close()
        }
    }

    fun delete(id: String) {
        val em: EntityManager = HibernateUtils.getEntityManager("appstock")
        try {
            em.transaction.begin()
            val producto = em.find(Producto::class.java, id)
            if (producto != null) {
                em.remove(producto)
                em.transaction.commit()
            } else {
                em.transaction.rollback()
                println("No se encontró el producto con id $id")
            }
        } catch (e: Exception) {
            em.transaction.rollback()
            e.printStackTrace()
        } finally {
            HibernateUtils.closeEntityManager(em)
        }
    }
}