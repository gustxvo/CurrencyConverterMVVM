package com.gustxvo.currencyconverter.data

import com.gustxvo.currencyconverter.BuildConfig.API_KEY
import com.gustxvo.currencyconverter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL = "https://api.apilayer.com/exchangerates_data/"

interface CurrencyApi {

    @Headers("apiKey: $API_KEY")
    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<CurrencyResponse>
}