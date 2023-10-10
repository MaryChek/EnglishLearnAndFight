package com.example.core.di

import com.example.core_api.qualifier.TranslateRetrofit
import com.example.core_api.qualifier.WordRetrofit
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    @WordRetrofit
    fun provideWordRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RANDOM_WORD_BASE_URL)
            .build()

    @Singleton
    @Provides
    @TranslateRetrofit
    fun provideTranslateRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(TRANSLATE_BASE_URL)
            .build()

    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideTranslateOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val builder: Request.Builder = originalRequest
                    .newBuilder()
                    .header("Content-Type", "application/json")
                val newRequest: Request = builder.build()
                chain.proceed(newRequest)
            }
            .addInterceptor(interceptor)
            .build()

    private const val RANDOM_WORD_BASE_URL = "https://random-word-api.herokuapp.com/"
    private const val TRANSLATE_BASE_URL = "https://libretranslate.de/"
}