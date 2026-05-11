package com.controldegastos.app.model

data class Gasto(
    val nombre: String = "",
    val monto: Double = 0.0,
    val categoria: String = "",
    val fecha: String = "",
    val userId: String = ""
)
