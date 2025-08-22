package com.ebc.calculadora_propina.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ebc.calculadora_propina.components.TipPercentageSelector
import com.ebc.calculadora_propina.viewModel.TipViewModel

@Preview
@Composable
fun TipScreen(
    navController: NavController = rememberNavController(),
    viewModel: TipViewModel = viewModel()
) {
    val billAmount by viewModel.billAmount.collectAsState()
    val tipPercent by viewModel.tipPercent.collectAsState()
    val customTipAmount by viewModel.customTipAmount.collectAsState()
    val total = 123

    //val isBillValid =

    val tipOptions = listOf(5, 10, 15, 20)

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        TipPercentageSelector(options = tipOptions)
        TipPercentageSelector()
        TipPercentageSelector()
    }
}