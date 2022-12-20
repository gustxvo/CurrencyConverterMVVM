package com.gustxvo.currencyconverter.main

import com.gustxvo.currencyconverter.data.CurrencyApi
import com.gustxvo.currencyconverter.data.model.CurrencyResponse
import com.gustxvo.currencyconverter.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val currencyApi: CurrencyApi
) : MainRepository {

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = currencyApi.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}