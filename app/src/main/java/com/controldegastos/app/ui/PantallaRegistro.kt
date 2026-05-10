package com.controldegastos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PantallaRegistro(
    irLogin: () -> Unit
) {

    // Firebase
    val auth = FirebaseAuth.getInstance()

    // Estados
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // Título
        Text(
            text = "Registro de Usuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Correo
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Confirmar contraseña
        OutlinedTextField(
            value = confirmarPassword,
            onValueChange = { confirmarPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botón registrar
        Button(
            onClick = {

                // Validar campos vacíos
                if (
                    email.isBlank() ||
                    password.isBlank() ||
                    confirmarPassword.isBlank()
                ) {

                    mensaje = "Complete todos los campos"
                    return@Button
                }

                // Validar contraseñas
                if (password != confirmarPassword) {

                    mensaje = "Las contraseñas no coinciden"
                    return@Button
                }

                // Validar longitud mínima
                if (password.length < 6) {

                    mensaje = "La contraseña debe tener al menos 6 caracteres"
                    return@Button
                }

                // Registrar usuario
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {

                        mensaje = "Usuario registrado correctamente"

                        // Ir al login
                        irLogin()
                    }
                    .addOnFailureListener {

                        mensaje = "Error: ${it.message}"
                    }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje
        Text(text = mensaje)

        Spacer(modifier = Modifier.height(24.dp))

        // Ir al login
        TextButton(
            onClick = { irLogin() },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}
