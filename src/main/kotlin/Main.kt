package org.example

import org.example.Model.*
import org.example.Repository.*
import org.example.Service.*
import java.util.*

fun login(sc: Scanner, usuarioService: UsuarioService): Boolean {
    var opcion: Int

    do {
        println("Seleccione una opción: ")
        println("1. Crear usuario")
        println("2. Acceder con mi usuario")
        println("3. Salir")

        opcion = sc.nextInt()
        sc.nextLine()

        when (opcion) {
            1 -> usuarioService.crearUsuario()
            2 -> {
                if (usuarioService.comprobarUsuario()) {
                    println("Acceso satisfactorio")
                    return false
                } else {
                    println("Credenciales Incorrectas.")
                }
            }
            3 -> {
                println("Hasta la proxima")
                return true
            }
            else -> println("Opcion no valida")
        }
    } while (true)
}


fun main() {

    val sc= Scanner(System.`in`)
    val usuarioService = UsuarioService(UsuarioRepository(), sc)
    val productoService = ProductoService(ProductoRepository(), ProveedorRepository(), sc)
    val proveedorService = ProveedorService(ProveedorRepository(), sc)


    val prueba= Proveedor("Monster Energy", "C/Escopeta")
    ProveedorRepository().create(prueba)

    var salidaMenu = false

    salidaMenu = login(sc,usuarioService)

    if(!salidaMenu){

        do {
            println("Menu de opciones: ")
            println("1. AltaProducto")
            println("2. BajaProducto")
            println("3. ModificarNombreProducto")
            println("4. ModificarStock")
            println("5. InformacionProducto")
            println("6. ProductosEnStock")
            println("7. ProductosSinStock")
            println("8. ProveedorPorId")
            println("9. ListaDeProveedores")
            println("0. Salir")

            var opcion = sc.nextInt()
            sc.nextLine()

            when(opcion) {

                1 -> productoService.altaProducto()
                2 -> productoService.bajaProducto()
                3 -> productoService.modificarNombreProducto()
                4 -> productoService.modificarStockProducto()
                5 -> productoService.obtenerProducto()
                6 -> productoService.obtenerProductosConStock()
                7 -> productoService.obtenerProductosSinStock()
                8 -> productoService.obtenerProveedorProducto()
                9 -> proveedorService.obtenerProveedores()
                0 -> println("Finalizando programa..")
                else -> println("Opción no válida. Por favor, intente nuevamente.")
            }

        }while(opcion != 0)
    }
}

