package com.example.mapservice.mapservice.di

import android.location.Geocoder
import com.example.mapservice.BuildConfig
import com.example.mapservice.mapservice.application.MapServiceApplication
import com.example.mapservice.mapservice.friendslist.api.FriendsListService
import com.example.mapservice.mapservice.websearch.api.KakaoHelper
import com.example.mapservice.mapservice.websearch.api.KakaoHelperImpl
import com.example.mapservice.mapservice.websearch.api.KakaoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    private val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val baseClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val kakaoInterceptor = object :Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder().addHeader("Authorization", BuildConfig.KAKAO_REST_TOKEN).build()
                return chain.proceed(request)
            }
        }
        val kakaoClient = baseClient.newBuilder().addNetworkInterceptor(kakaoInterceptor).build()

        return kakaoClient
    }

    @Provides
    @Singleton
    fun provideKakaoRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.KAKAO_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideKakaoApiService(retrofit: Retrofit): KakaoApiService {
        return retrofit.create(KakaoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoApiHelper(retrofitHelperImpl: KakaoHelperImpl): KakaoHelper = retrofitHelperImpl

    @Provides
    @Singleton
    fun provideReqresRetrofit(): FriendsListService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.REQRES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(FriendsListService::class.java)
    }
}