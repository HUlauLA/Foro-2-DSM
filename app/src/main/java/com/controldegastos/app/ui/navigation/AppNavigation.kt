package com.example.tugastoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugastoapp.ui.screens.PantallaLogin
import com.example.tugastoapp.ui.screens.PantallaRegistro
import com.example.tugastoapp.ui.screens.PantallaPrincipal

@Composable
fun AppNavigation(navController: NavHostController) {
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

        composable("pantallaPrincipal") {
            PantallaPrincipal(navController)
        }
    }
}
