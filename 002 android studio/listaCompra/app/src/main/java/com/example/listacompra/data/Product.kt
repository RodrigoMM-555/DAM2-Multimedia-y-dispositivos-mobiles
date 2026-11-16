package com.example.listacompra.data

data class Producto(
    val id: Int,
    val nombre: String,
    val comprado: Boolean = false
)