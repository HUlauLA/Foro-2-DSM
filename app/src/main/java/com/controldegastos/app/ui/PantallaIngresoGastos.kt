package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.controldegastos.app.model.Gasto

@Composable
fun PantallaIngresoGastos(
    irHistorial: () -> Unit
)

    // Firebase
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    // Estados
    var nombreGasto by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Ingreso de Gastos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
        OutlinedTextField(
            value = nombreGasto,
            onValueChange = { nombreGasto = it },
            label = { Text("Nombre del gasto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Monto
        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Categoría
        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón guardar
        Button(
            onClick = {

                if (
                    nombreGasto.isBlank() ||
                    monto.isBlank() ||
                    fecha.isBlank() ||
                    categoria.isBlank()
                ) {
                    mensaje = "Complete todos los campos"
                    return@Button
                }

                val gasto = Gasto(
                    nombre = nombreGasto,
                    monto = monto.toDoubleOrNull() ?: 0.0,
                    fecha = fecha,
                    categoria = categoria,
                    userId = auth.currentUser?.uid ?: ""
                )

                db.collection("gastos")
                    .add(gasto)
                    .addOnSuccessListener {

                        mensaje = "Gasto guardado correctamente "

                        nombreGasto = ""
                        monto = ""
                        fecha = ""
                        categoria = ""
                    }
                    .addOnFailureListener {
                        mensaje = "Error al guardar "
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar gasto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = mensaje)
    }
}
