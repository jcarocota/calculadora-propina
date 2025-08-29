package com.ebc.calculadora_propina.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

class TipViewModel: ViewModel() {
    private val _billAmount = MutableStateFlow("")
    val billAmount: StateFlow<String> = _billAmount

    private val _tipPercent = MutableStateFlow<Int?>(null)
    val tipPercent: StateFlow<Int?> = _tipPercent

    private val _customTipAmount = MutableStateFlow("")
    val customTipAmount: StateFlow<String> = _customTipAmount

    val totalToPay: StateFlow<String> = combine(
        _billAmount,
        _tipPercent,
        _customTipAmount
    ) {
        billAmountStr, tipPercent, customTipAmountStr ->
        val billAmount = billAmountStr.toDoubleOrNull() ?: 0.0
        val customTipAmount = customTipAmountStr.toDoubleOrNull()

        val tip = if(
            customTipAmountStr.isNotBlank() &&
            customTipAmount != null &&
            customTipAmount >0) {
            customTipAmount
        } else {
            billAmount * ((tipPercent ?: 0) / 100)
        }

        val formatted = NumberFormat.getNumberInstance(Locale.US).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

        formatted.format(billAmount + tip)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        "0.00")

    fun setBillAmount(value: String) {
        _billAmount.value = value
    }

    fun setTipPercent(value: Int?) {
        _tipPercent.value = value
        _customTipAmount.value = ""
    }

    fun setCustomTipAmount(value: String) {
        _customTipAmount.value = value
        _tipPercent.value = null
    }

    fun reset() {
        _billAmount.value = ""
        _tipPercent.value = null
        _customTipAmount.value = ""
    }
}