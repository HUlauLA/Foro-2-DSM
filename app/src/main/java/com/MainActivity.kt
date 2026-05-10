package com.controldegastos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import com.controldegastos.app.ui.PantallaHistorialGastos
import com.controldegastos.app.ui.PantallaIngresoGastos
import com.controldegastos.app.ui.theme.ControlDeGastosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            ControlDeGastosTheme {

                var pantallaActual by remember {
                    mutableStateOf("ingreso")
                }

                Scaffold {

                    when (pantallaActual) {

                        // Pantalla ingreso
                        "ingreso" -> PantallaIngresoGastos(

                            irHistorial = {
                                pantallaActual = "historial"
                            }

                        )

                        // Pantalla historial
                        "historial" -> PantallaHistorialGastos(

                            volver = {
                                pantallaActual = "ingreso"
                            }

                        )
                    }
                }
            }
        }
    }
}
