package org.example.Service

import org.example.Repository.*
import java.util.*

class ProveedorService (val proveedorRepository : ProveedorRepository, val sc : Scanner){

    fun obtenerProveedores() {

        val proveedores = proveedorRepository.readAll()

        if (proveedores.isEmpty()) {
            println("No hay proveedores")
        } else {
            for (proveedor in proveedores) {
                println("$proveedor")
            }
        }
    }
}