package org.example.Service

import org.example.Model.*
import org.example.Repository.*
import java.util.*

class ProductoService(val productoRepository: ProductoRepository, val proveedorRepository: ProveedorRepository, val sc: Scanner) {

    fun altaProducto() {
        println("ID del proveedor: ")
        val idProveedor = sc.nextLong()
        sc.nextLine()

        var proveedor = proveedorRepository.read(idProveedor)

        if (proveedor != null) {
            println("Nombre: ")
            val nombreProducto = sc.nextLine()

            println("Categoría: ")
            val categoria = sc.nextLine()

            println("Descripción: ")
            val descripcion = sc.nextLine()

            println("Precio sin IVA: ")
            val precioSinIva = sc.nextFloat()
            sc.nextLine()

            println("Stock del producto: ")
            val stock = sc.nextInt()
            sc.nextLine()

            val nuevoProducto = Producto(categoria, nombreProducto, descripcion, precioSinIva, stock, proveedor)

            proveedor.addProducto(nuevoProducto)
            proveedorRepository.update(proveedor)

            println("Producto añadido exitosamente.")
        } else {
            println("Esa referencia no existe")
        }
    }


    fun bajaProducto() {
        println("ID del producto a dar de baja: ")
        val idProducto = sc.nextLine()

        val productoAeliminar = productoRepository.read(idProducto)

        if (productoAeliminar != null) {
            val proveedor = productoRepository.readProveedorProducto(idProducto)

            if (proveedor != null) {
                proveedor.removeProducto(productoAeliminar)
                proveedorRepository.update(proveedor)
                productoRepository.delete(idProducto)
            }
        } else {
            println("No se ha encontrado un producto con esa ID")
        }
    }

    fun modificarNombreProducto() {
        println("ID del producto a modificar: ")
        val idProducto = sc.nextLine()

        println("Nuevo nombre : ")
        val nuevoNombre = sc.nextLine()

        productoRepository.updateProductoNombre(idProducto, nuevoNombre)
    }

    fun modificarStockProducto() {
        println("ID del producto para modificar: ")
        val idProducto = sc.nextLine()

        println("Nuevo stock disponible: ")
        val stock = sc.nextInt()
        sc.nextLine()

        productoRepository.updateProductoStock(idProducto, stock)
    }

    fun obtenerProducto() {
        println("Introduzca la ID del producto: ")
        val idProducto = sc.nextLine()

        val producto = productoRepository.read(idProducto)

        if (producto != null) {
            println(producto)

        } else {
            println("La Id no coincide con ningun producto")
        }
    }

    fun obtenerProductosConStock() {
        val productos = productoRepository.readProductoConStock()
        println("Cantidad de productos: ${productos.size}")

        if (productos.isNotEmpty()) {
            for (producto in productos)
                println(producto)

        } else {
            println("No hay stock")
        }
    }

    fun obtenerProductosSinStock() {
        val productos = productoRepository.readProductoSinStock()

        if (productos.isEmpty()) {
            println("No hay productos sin stock.")
        } else {
            for (producto in productos) {
                println(producto)
            }
        }
    }
    fun obtenerProveedorProducto() {
        println("Introduzca ID del producto: ")
        val idProducto = sc.nextLine()

        val proveedor = productoRepository.readProveedorProducto(idProducto)

        if (proveedor != null) {
            println(proveedor)

        } else {
            println("Proveedor no existe")
        }
    }
}
