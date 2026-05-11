package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.controldegastos.app.modelo.Gasto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PantallaHistorialGastos(navController: NavController) {

    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    var listaGastos by remember {
        mutableStateOf(listOf<Gasto>())
    }

    LaunchedEffect(Unit) {

        db.collection("gastos")
            .whereEqualTo("userId", auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { result ->

                val lista = mutableListOf<Gasto>()

                for (document in result) {
                    val gasto = document.toObject(Gasto::class.java)
                    lista.add(gasto)
                }

                listaGastos = lista
            }
    }

    val total = listaGastos.sumOf { it.monto }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Historial de Gastos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Total Mensual: $$total",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(listaGastos) { gasto ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("Nombre: ${gasto.nombre}")
                        Text("Monto: $${gasto.monto}")
                        Text("Categoría: ${gasto.categoria}")
                        Text("Fecha: ${gasto.fecha}")
                    }
                }
            }
        }
    }
}
