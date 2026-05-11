package com.controldegastos.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.controldegastos.app.screens.LoginScreen
import com.controldegastos.app.screens.PantallaHistorialGastos
import com.controldegastos.app.screens.PantallaIngresoGastos
import com.controldegastos.app.screens.RegistroScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("registro") {
            RegistroScreen(navController)
        }

        composable("ingreso") {
            PantallaIngresoGastos(navController)
        }

        composable("historial") {
            PantallaHistorialGastos(navController)
        }
    }
}
