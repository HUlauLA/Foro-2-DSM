package com.controldegastos.app

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.LocalTextStyle
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    val context = LocalContext.current
    val activity = context as Activity

    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    val googleSignInClient = GoogleSignIn.getClient(
        context,
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("646039353432-1cmmbf15dn1bgogcfqhmmp106i5hs1va.apps.googleusercontent.com")
            .requestEmail()
            .build()
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        onLoginSuccess()
                    } else {
                        mensaje = "Error con Google"
                    }
                }
        } catch (e: Exception) {
            mensaje = "No se pudo iniciar con Google"
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Control de Gastos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (correo.isBlank() || password.isBlank()) {
                    mensaje = "Complete todos los campos"
                    return@Button
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    mensaje = "Correo inválido"
                    return@Button
                }

                if (password.length < 6) {
                    mensaje = "Mínimo 6 caracteres"
                    return@Button
                }

                cargando = true
                mensaje = ""

                auth.signInWithEmailAndPassword(correo, password)
                    .addOnCompleteListener { task ->
                        cargando = false
                        if (task.isSuccessful) {
                            onLoginSuccess()
                        } else {
                            mensaje = "Error al iniciar sesión"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            ),
            enabled = !cargando
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                if (correo.isBlank() || password.isBlank()) {
                    mensaje = "Complete todos los campos"
                    return@OutlinedButton
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    mensaje = "Correo inválido"
                    return@OutlinedButton
                }

                if (password.length < 6) {
                    mensaje = "Mínimo 6 caracteres"
                    return@OutlinedButton
                }

                cargando = true
                mensaje = ""

                auth.createUserWithEmailAndPassword(correo, password)
                    .addOnCompleteListener { task ->
                        cargando = false
                        if (task.isSuccessful) {
                            mensaje = "Usuario registrado correctamente"
                        } else {
                            mensaje = "Error al registrar"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            ),
            enabled = !cargando
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (cargando) {
            CircularProgressIndicator()
        }

        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                googleSignInClient.signOut().addOnCompleteListener {
                    val signInIntent: Intent = googleSignInClient.signInIntent
                    launcher.launch(signInIntent)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )

        ) {
            Text("Iniciar sesión con Google")
        }
    }
}