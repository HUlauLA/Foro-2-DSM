package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal(
    irIngreso: () -> Unit,
    irHistorial: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Menú Principal",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = irIngreso,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar gasto")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = irHistorial,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver historial")
        }
    }
}
