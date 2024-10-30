package org.example.Model

import  jakarta.persistence.*

@Entity
@Table(name = "usuarios")
class Usuario(

    @Id
    @Column(name = "nombre_usuario")
    val nombreUsuario: String,

    @Column(nullable = false, length = 20)
    var password: String
) {
}