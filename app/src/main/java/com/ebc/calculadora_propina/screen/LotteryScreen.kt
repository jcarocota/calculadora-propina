package com.ebc.calculadora_propina.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebc.calculadora_propina.viewModel.LoteriaUiState
import com.ebc.calculadora_propina.viewModel.LotteryViewModel

@Composable
fun LotteryScreen(vm: LotteryViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()

    // para saber si está cargando y deshabilitar el botón
    val isLoading = state is LoteriaUiState.Loading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Zona de contenido principal centrada
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (val s = state) {
                is LoteriaUiState.Loading -> CircularProgressIndicator()
                is LoteriaUiState.Error -> Text("Error: ${s.message}")
                is LoteriaUiState.Success -> LoteriaBallsRow(numbers = s.numbers)
            }
        }

        Spacer(Modifier.height(28.dp))

        Button(
            onClick = { vm.load() },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¡Nuevos números!")
        }
    }
}

@Composable
fun LoteriaBallsRow(numbers: List<Int>) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        numbers.take(6).forEach { n ->
            Ball(number = n)
        }
    }
}

@Composable
fun Ball(number: Int) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(color = Color(0xFFD32F2F), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}