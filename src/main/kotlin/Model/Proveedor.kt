package org.example.Model

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
class Proveedor(

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val direccion: String,


    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val productos: MutableList<Producto> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    fun addProducto(producto: Producto) {
        productos.add(producto)
        producto.proveedor = this
    }

    fun removeProducto(producto: Producto) {
        productos.remove(producto)
    }

    override fun toString(): String {
        return "[$nombre] Dirección: $direccion, Id: $id, Productos: $productos"
    }
}