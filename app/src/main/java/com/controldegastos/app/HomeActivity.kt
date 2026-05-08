package com.controldegastos.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val txtSaludo = findViewById<TextView>(R.id.txtSaludo)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnHistorial = findViewById<Button>(R.id.btnHistorial)

        val nombre = "Usuario"
        txtSaludo.text = "Bienvenida, $nombre"

        btnAgregar.setOnClickListener {
            // Aquí luego abriremos otra pantalla
        }

        btnHistorial.setOnClickListener {
            // Aquí luego veremos historial
        }
    }
}