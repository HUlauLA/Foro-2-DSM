package com.controldegastos.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.controldegastos.app.ui.PantallaLogin
import com.controldegastos.app.ui.PantallaRegistro
import com.controldegastos.app.ui.PantallaPrincipal

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN
        composable("login") {
            PantallaLogin(
                irARegistro = { navController.navigate("registro") },
                irAInicio = { navController.navigate("pantallaPrincipal") }
            )
        }

        // REGISTRO
        composable("registro") {
            PantallaRegistro(
                volverALogin = { navController.navigate("login") }
            )
        }

        // PANTALLA PRINCIPAL
        composable("pantallaPrincipal") {
            PantallaPrincipal(
                volver = { navController.popBackStack() }
            )
        }
    }
}
