package com.ebc.calculadora_propina.navigation

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ebc.calculadora_propina.screen.TipScreen
import com.ebc.calculadora_propina.viewModel.TipViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: TipViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            TipScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "thankyou") {
            Text(text = "Fin!!!")
        }
    }
}