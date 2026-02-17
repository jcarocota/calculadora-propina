package com.ebc.calculadora_propina.network

import retrofit2.http.GET

interface LotteryApi {
    @GET("loteria")
    suspend fun getLoteriaRaw(): String
}