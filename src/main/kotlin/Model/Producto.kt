package org.example.Model

import jakarta.persistence.*
import java.time.Instant
import java.util.Date

@Entity
@Table(name="Productos")
class Producto (

    @Column(name = "Nombre", nullable = true,length = 20)
    val nombre : String,

    @Column(name = "Categoria", nullable = false)
    val categoria : String,

    @Column(name = "Descripcion", nullable = false, length = 30)
    val descripcion : String,

    @Column(name = "PrecioSinIva", nullable = true)
    val precioSinIva : Float,

    @Column(name = "Stock", nullable = true)
    val stock : Int,

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "proveedor_id", nullable = false)
    var proveedor: Proveedor?,

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date = Date.from(Instant.now()),

    @Id
    @Column(unique = true)
    var id: String
){

    val precioConIva: Float
        get() = precioSinIva * 1.21f

    override fun toString(): String {
        return "Producto(nombre='$nombre', categoria='$categoria', descripcion='$descripcion'," +
                " precioSinIva=$precioSinIva, stock=$stock, proveedor=$proveedor, fechaAlta=$fechaAlta, id='$id', " +
                "precioConIva=$precioConIva)"
    }


}