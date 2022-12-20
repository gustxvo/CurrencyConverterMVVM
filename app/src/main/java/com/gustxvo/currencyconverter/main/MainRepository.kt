package com.gustxvo.currencyconverter.main

import com.gustxvo.currencyconverter.data.model.CurrencyResponse
import com.gustxvo.currencyconverter.util.Resource

interface MainRepository {

    suspend fun getRates(base: String): Resource<CurrencyResponse>
}