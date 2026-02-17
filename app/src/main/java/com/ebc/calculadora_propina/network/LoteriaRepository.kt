package com.ebc.calculadora_propina.network

import kotlinx.serialization.json.Json


class LoteriaRepository(
    private val api: LotteryApi = Network.lotteryApi
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getLoteria(): List<Int> {
        val raw = api.getLoteriaRaw()
        return json.decodeFromString(raw) // List<Int>
    }

}