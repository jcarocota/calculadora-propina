package com.ebc.calculadora_propina.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TipViewModel: ViewModel() {
    private val _billAmount = MutableStateFlow("")
    val billAmount: StateFlow<String> = _billAmount

    private val _tipPercent = MutableStateFlow<Int?>(null)
    val tipPercent: StateFlow<Int?> = _tipPercent

    private val _customTipAmount = MutableStateFlow("")
    val customTipAmount: StateFlow<String> = _customTipAmount

    fun setBillAmount(value: String) {
        _billAmount.value = value
    }

    fun setTipPercent(value: Int?) {
        _tipPercent.value = value
    }

    fun setCustomTipAmount(value: String) {
        _customTipAmount.value = value
    }
}