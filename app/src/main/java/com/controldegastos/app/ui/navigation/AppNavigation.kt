package com.controldegastos.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.controldegastos.app.ui.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            PantallaLogin(navController)
        }

        composable("registro") {
            PantallaRegistro(navController)
        }

        composable("principal") {
            PantallaPrincipal(navController)
        }

        composable("ingreso") {
            PantallaIngresoGastos(navController)
        }

        composable("historial") {
            PantallaHistorialGastos(navController)
        }
    }
}
