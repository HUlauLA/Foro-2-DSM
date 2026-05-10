@Composable
fun PantallaLogin(onLoginCorrecto: () -> Unit) {

    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") }
        )

        Button(
            onClick = {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        onLoginCorrecto()
                    }
                    .addOnFailureListener {
                        mensaje = "Error al iniciar sesión"
                    }

            }
        ) {
            Text("Iniciar sesión")
        }

        Button(
            onClick = {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        mensaje = "Usuario registrado"
                    }
                    .addOnFailureListener {
                        mensaje = "Error al registrar"
                    }

            }
        ) {
            Text("Registrarse")
        }

        Text(mensaje)
    }
}
