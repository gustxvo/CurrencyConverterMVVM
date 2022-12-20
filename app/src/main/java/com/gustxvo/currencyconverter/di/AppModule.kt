package com.gustxvo.currencyconverter.di

import com.gustxvo.currencyconverter.data.BASE_URL
import com.gustxvo.currencyconverter.data.CurrencyApi
import com.gustxvo.currencyconverter.main.DefaultMainRepository
import com.gustxvo.currencyconverter.util.DispatcherProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(moshi: Moshi): CurrencyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(MoshiConverterFactory::class).build()

    @Singleton
    @Provides
    fun provideMainRepository(
        currencyApi: CurrencyApi
    ): DefaultMainRepository = DefaultMainRepository(currencyApi)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {

        override val main: CoroutineDispatcher
            get() = Dispatchers.Main

        override val io: CoroutineDispatcher
            get() = Dispatchers.IO

        override val default: CoroutineDispatcher
            get() = Dispatchers.Default

        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}