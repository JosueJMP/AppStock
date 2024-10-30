package org.example.Services

import org.example.Repository.UsuarioRepository


class UsuarioService(val repository: UsuarioRepository) {

    fun crearUsuario() {
        println("Introduce el nombre del usuario")
        val nombre = readLine()
        println("Introduce la contraseña")
        val password = readLine()

    }
}