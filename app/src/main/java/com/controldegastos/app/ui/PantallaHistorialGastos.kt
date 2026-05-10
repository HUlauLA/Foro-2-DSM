package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.controldegastos.app.model.Gasto

@Composable
fun PantallaHistorialGastos() {

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    var listaGastos by remember { mutableStateOf(listOf<Gasto>()) }

    val userId = auth.currentUser?.uid

    // Cargar datos desde Firebase
    LaunchedEffect(userId) {

        if (userId != null) {

            db.collection("gastos")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, _ ->

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

        Text(
            text = "Historial de Gastos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de gastos
        LazyColumn {

            items(listaGastos) { gasto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(text = gasto.nombre, style = MaterialTheme.typography.titleMedium)

                        Text(text = "Monto: $${gasto.monto}")

                        Text(text = "Categoría: ${gasto.categoria}")

                        Text(text = "Fecha: ${gasto.fecha}")
                    }
                }
            }
        }
    }
}
