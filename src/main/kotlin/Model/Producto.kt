package org.example.Model

import jakarta.persistence.*
import java.time.Instant
import java.util.Date

@Entity
@Table(name = "Productos")
class Producto (

    @Column(name = "Categoria")
    val categoria : String?,

    @Column(name = "Nombre", nullable = false)
    var nombre : String?,

    @Column(name = "Descripcion")
    val descripcion : String?,

    @Column(name = "precioSinIVA")
    var precioSinIva : Float?,

    @Column(name = "precioConIVA")
    var precioConIva : Float? = precioSinIva?.times(1.21f),

    @Column(name = "fechaALta")
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date = Date.from(Instant.now()),

    @Column(name = "Stock")
    var stock : Int?,

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "idProveedor")
    var proveedor : Proveedor?,

    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id : String,

    ){
    //constructor
    constructor(categoria: String, nombre: String, descripcion: String, precioSinIva: Float, stock: Int, proveedor: Proveedor?)
            : this(categoria, nombre, descripcion, precioSinIva, precioSinIva * 1.21f, Date.from(Instant.now()), stock, proveedor,
        "${categoria.take(3)}${nombre.take(3)}${proveedor?.nombre?.take(3)}")

    // Obtener información
    override fun toString(): String {
        return "Producto(id='$id', categoria='$categoria', nombre='$nombre', descripcion='$descripcion', " +
                "precioSinIva=$precioSinIva, precioConIva=$precioConIva, fechaAlta=$fechaAlta, stock=$stock, " +
                "proveedor=${proveedor?.nombre})"
    }
}



