package com.example.rxjavasample.di


import com.example.rxjavasample.BuildConfig
import com.example.rxjavasample.util.Constants
import com.example.rxjavasample.searchmovie.api.SearchMovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    private val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val baseClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideNaverOkHttpClient() :OkHttpClient {
        val naverInterceptor = object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
                    .build()

                return chain.proceed(request)
            }
        }
        val naverClient = baseClient.newBuilder().addNetworkInterceptor(naverInterceptor).build()
        return naverClient
    }
    @Provides
    @Singleton
    fun provideNaverURL() = Constants.NAVER_API_URL

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, NAVER_API_URL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NAVER_API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchMovieService(retrofit: Retrofit) = retrofit.create(SearchMovieService::class.java)
}