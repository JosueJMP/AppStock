package org.example.Service

import org.example.Model.*
import org.example.Repository.*
import java.util.*

class UsuarioService(val usuarioRepository : UsuarioRepository, val sc : Scanner) {

    fun crearUsuario() {
        println("Introduce el nombre de usuario:")
        val userName: String = sc.nextLine().trim()

        println("Introduce la password:")
        val password: String = sc.nextLine().trim()

        if (userName.isNotEmpty() && password.isNotEmpty()) {
            val usuario = usuarioRepository.read(userName)

            if (usuario == null) {
                val nuevoUsuario = Usuario(password, userName)
                usuarioRepository.create(nuevoUsuario)
            } else {
                println("Usuario existente")
            }
        } else {
            println("Nombre de usuario y contraseña no pueden estar vacíos.")
        }
    }

    fun comprobarUsuario() : Boolean {

        println("Introduzca su nombre de usuario: ")
        val userName : String = sc.nextLine()

        println("Introduzca su password: ")
        val password : String = sc.nextLine()

        val posibleUsuario = usuarioRepository.read(userName)

        return if(posibleUsuario != null){
            posibleUsuario.password == password

        }else{
            println("Credenciales Incorrectas")
            false
        }
    }
}