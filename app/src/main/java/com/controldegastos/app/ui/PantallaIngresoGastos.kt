package com.controldegastos.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.controldegastos.app.firebase.AuthManager
import com.controldegastos.app.firebase.FirestoreManager
import com.controldegastos.app.model.Gasto

@Composable
fun PantallaIngresoGastos(navController: NavController) {

    val firestoreManager = FirestoreManager()
    val authManager = AuthManager()

    var nombre by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Agregar Gasto")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            val
