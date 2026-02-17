package com.ebc.calculadora_propina.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.calculadora_propina.network.LoteriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface LoteriaUiState {
    data object Loading : LoteriaUiState
    data class Success(val numbers: List<Int>) : LoteriaUiState
    data class Error(val message: String) : LoteriaUiState
}

class LotteryViewModel(
    private val repo: LoteriaRepository = LoteriaRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow<LoteriaUiState>(LoteriaUiState.Loading)
    val uiState: StateFlow<LoteriaUiState> = _uiState

    fun load() {
        viewModelScope.launch {
            _uiState.value = LoteriaUiState.Loading
            runCatching { repo.getLoteria() }
                .onSuccess { _uiState.value = LoteriaUiState.Success(it) }
                .onFailure { _uiState.value = LoteriaUiState.Error(it.message ?: "Error") }
        }
    }

    init { load() }

}