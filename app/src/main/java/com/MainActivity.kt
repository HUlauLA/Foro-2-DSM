class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val auth = FirebaseAuth.getInstance()

        setContent {
            ControlDeGastosTheme {

                var pantallaActual by remember {
                    mutableStateOf(
                        if (auth.currentUser == null) "login" else "ingreso"
                    )
                }

                Scaffold {
                    when (pantallaActual) {

                        "login" -> PantallaLogin(
                            onLoginSuccess = {
                                pantallaActual = "ingreso"
                            }
                        )

                        "ingreso" -> PantallaIngresoGastos(
                            irHistorial = { pantallaActual = "historial" }
                        )

                        "historial" -> PantallaHistorialGastos(
                            volver = { pantallaActual = "ingreso" }
                        )
                    }
                }
            }
        }
    }
}
