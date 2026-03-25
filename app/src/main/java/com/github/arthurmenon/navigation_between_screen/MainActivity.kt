package com.github.arthurmenon.navigation_between_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.arthurmenon.navigation_between_screen.screens.LoginScreen
import com.github.arthurmenon.navigation_between_screen.screens.MenuScreen
import com.github.arthurmenon.navigation_between_screen.screens.PedidosScreen
import com.github.arthurmenon.navigation_between_screen.screens.PerfilScreen
import com.github.arthurmenon.navigation_between_screen.ui.theme.Android5navigationbetweenscreensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android5navigationbetweenscreensTheme  {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "pedidos") {
                            PedidosScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable(route = "perfil/{nome}") {
                            val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
                            PerfilScreen(modifier = Modifier.padding(innerPadding), navController, nome!!)
                        }
                    }
                }
            }
        }
    }
}
