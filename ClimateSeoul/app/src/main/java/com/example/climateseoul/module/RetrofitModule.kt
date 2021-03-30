package com.example.climateseoul.module

import com.example.climateseoul.BuildConfig
import com.example.climateseoul.climateinfo.api.ClimateInfoService
import com.example.climateseoul.climateinfo.repository.ClimateInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val okHttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val baseClient = OkHttpClient.Builder()
        .addInterceptor(okHttpLoggingInterceptor)
        .writeTimeout(20,TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder()
                    .build()

                return chain.proceed(request)
            }
        }
        val client = baseClient.newBuilder().addNetworkInterceptor(interceptor).build()
        return client
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SEOUL_CLIMATE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideClimateService(retrofit: Retrofit): ClimateInfoService =
        retrofit.create(ClimateInfoService::class.java)

}