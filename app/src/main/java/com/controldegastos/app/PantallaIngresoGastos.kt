package com.controldegastos.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun PantallaIngresoGastos() {

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

        OutlinedTextField(
            value = nombreGasto,
            onValueChange = { nombreGasto = it },
            label = { Text("Nombre del gasto") },
            placeholder = { Text("Ejemplo: Gasolina") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            placeholder = { Text("Ejemplo: 25.00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            placeholder = { Text("Ejemplo: 09/05/2026") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría") },
            placeholder = { Text("Ejemplo: Comida, Transporte, Salud") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (
                    nombreGasto.isBlank() ||
                    monto.isBlank() ||
                    fecha.isBlank() ||
                    categoria.isBlank()
                ) {
                    mensaje = "Complete todos los campos."
                } else if (monto.toDoubleOrNull() == null || monto.toDouble() <= 0) {
                    mensaje = "Ingrese un monto válido."
                } else {
                    mensaje = "Gasto registrado correctamente."

                    nombreGasto = ""
                    monto = ""
                    fecha = ""
                    categoria = ""
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