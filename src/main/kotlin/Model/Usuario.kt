package org.example.Model

import jakarta.persistence.*

@Entity
@Table(name = "Usuarios")
class Usuario(


    @Column(name = "Contraseña", nullable = false, length = 20)
    val password: String,

    @Id
    @Column(name = "nombreUsuario", nullable = false)
    val nombreUsuario: String,
)