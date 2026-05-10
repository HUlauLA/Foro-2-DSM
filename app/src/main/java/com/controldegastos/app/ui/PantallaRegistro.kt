package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PantallaRegistro(
    volverALogin: () -> Unit
) {

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }

    var mensajeError by remember { mutableStateOf<String?>(null) }
    var mensajeExito by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Crear Cuenta",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmarPassword,
            onValueChange = { confirmarPassword = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botón registrar
        Button(
            onClick = {

                if (password != confirmarPassword) {
                    mensajeError = "Las contraseñas no coinciden"
                    return@Button
                }

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { result ->

                        val userId = result.user?.uid ?: ""

                        // Guardar datos básicos del usuario en Firestore
                        val datosUsuario = hashMapOf(
                            "email" to email,
                            "userId" to userId
                        )

                        db.collection("usuarios").document(userId)
                            .set(datosUsuario)

                        mensajeExito = "Cuenta creada exitosamente"
                        mensajeError = null
                    }
                    .addOnFailureListener { e ->
                        mensajeError = e.localizedMessage
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarme")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Volver a login
        TextButton(
            onClick = { volverALogin() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ya tengo una cuenta")
        }

        // Mensajes
        mensajeError?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        mensajeExito?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it, color = MaterialTheme.colorScheme.primary)
        }
    }
}
