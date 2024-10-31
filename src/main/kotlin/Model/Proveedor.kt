package org.example.Model

import jakarta.persistence.*

@Entity
@Table(name = "Proveedores")
class Proveedor(

    @Column(name = "Nombre", nullable = false)
    var nombre: String?,

    @Column(name = "Direccion")
    val direccion: String?,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "Proveedor", orphanRemoval = true, fetch = FetchType.EAGER)
    var productos: MutableList<Producto> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

)
{

    fun addProducto(producto: Producto) {
        productos.add(producto)
        producto.proveedor = this
    }

    fun removeProducto(producto: Producto) {
        productos.remove(producto)
    }

    //Para obtener un proveedor
    override fun toString(): String {
        return "[$nombre] Dirección: $direccion, Id: $id, Productos: $productos"
    }
}

