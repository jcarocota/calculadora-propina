package com.ebc.calculadora_propina.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ebc.calculadora_propina.R
import com.ebc.calculadora_propina.components.TipPercentageSelector
import com.ebc.calculadora_propina.viewModel.TipViewModel

@Preview(showBackground = true)
@Composable
fun TipScreen(
    navController: NavController = rememberNavController(),
    viewModel: TipViewModel = viewModel()
) {
    val billAmount by viewModel.billAmount.collectAsState()
    val tipPercent by viewModel.tipPercent.collectAsState()
    val customTipAmount by viewModel.customTipAmount.collectAsState()
    val total by viewModel.totalToPay.collectAsState()

    val isBillValid = (
            billAmount.toDoubleOrNull() != null &&
            billAmount.toDouble() > 0
            )

    val tipOptions = listOf(5, 10, 15, 20)

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.bill)
    )

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Text(text = "Total de la cuenta")
        TextField(
            value = billAmount,
            onValueChange = viewModel::setBillAmount,
            /*onValueChange = {
                viewModel.setBillAmount(it)
            },*/
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Selecciona un porcentaje de propina")
        TipPercentageSelector(
            options = tipOptions,
            selected = tipPercent,
            onSelected = viewModel::setTipPercent,
            enabled = isBillValid
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "¿Propina personalizada?")
        TextField(
            value = customTipAmount,
            onValueChange = viewModel::setCustomTipAmount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Total a pagar: $total",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = viewModel::reset
            ) {
                Text(text = "Reiniciar")
            }
            Button(
                onClick = {
                    navController.navigate("thankyou")
                }
            ) {
                Text(text = "Pagar")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .width(300.dp)
            )
        }



    }
}