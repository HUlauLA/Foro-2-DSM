package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.controldegastos.app.model.Gasto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PantallaHistorialGastos(
    volver: () -> Unit
) {

    // Firebase
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    // Lista de gastos
    var listaGastos by remember {
        mutableStateOf(listOf<Gasto>())
    }

    // Total mensual
    val totalMensual = listaGastos.sumOf { it.monto }

    val userId = auth.currentUser?.uid

    // Cargar gastos desde Firebase
    LaunchedEffect(userId) {

        if (userId != null) {

            db.collection("gastos")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, error ->

                    if (snapshot != null) {

                        val gastos = snapshot.documents.mapNotNull { doc ->

                            doc.toObject(Gasto::class.java)
                        }

                        listaGastos = gastos
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Título
        Text(
            text = "Historial de Gastos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Total mensual
        Text(
            text = "Total mensual: $$totalMensual",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de gastos
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(listaGastos) { gasto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = gasto.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(text = "Monto: $${gasto.monto}")

                        Text(text = "Categoría: ${gasto.categoria}")

                        Text(text = "Fecha: ${gasto.fecha}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón volver
        Button(
            onClick = { volver() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
