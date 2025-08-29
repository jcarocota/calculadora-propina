package com.ebc.calculadora_propina.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
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
import com.ebc.calculadora_propina.viewModel.TipViewModel

@Preview(showBackground = true)
@Composable
fun ThankyouScreen(
    navController: NavController = rememberNavController(),
    viewModel: TipViewModel = viewModel()    
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.thanks)
    )

    val total by viewModel.totalToPay.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gracias por su compra",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Total pagado: $ $total",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(15.dp))

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(400.dp)
        )
    }


    
}