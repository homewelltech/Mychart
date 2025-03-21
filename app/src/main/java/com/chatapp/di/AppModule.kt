// AppModule.kt (依赖注入模块)
package com.chatapp.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.chatapp.api.UserApiService
import com.chatapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://192.168.10.119:8085"


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()

            val isMultipart = request.body?.contentType()?.type == "multipart"
            val isStreamOrFile = request.body?.contentType()?.subtype?.contains("octet-stream") == true

            // 请求日志
            if (isMultipart || isStreamOrFile) {
                Log.d("API_REQUEST", "🚀 Sending multipart/file request: ${request.url}\nHeaders: ${request.headers}")
            } else {
                val requestBodyString = request.body?.let { body ->
                    try {
                        val buffer = okio.Buffer()
                        body.writeTo(buffer)
                        buffer.readUtf8()
                    } catch (e: Exception) {
                        "body read error"
                    }
                } ?: "No body"

                Log.d("API_REQUEST", "📤 Sending request: ${request.url}\nHeaders: ${request.headers}\nBody: $requestBodyString")
            }

            val response = chain.proceed(request)

            val responseContentType = response.body?.contentType()?.subtype ?: ""
            val isResponseStream = responseContentType.contains("octet-stream") || responseContentType.contains("image") || responseContentType.contains("video")

            // 响应日志
            if (isResponseStream) {
                Log.d("API_RESPONSE", "📥 Received file response: ${response.request.url}\nStatus: ${response.code}\nContent-Type: $responseContentType")
            } else {
                Log.d("API_RESPONSE", "✅ Received response: ${response.request.url}\nStatus: ${response.code}")
            }

            response
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // ✅ 更新 API Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }



    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("chatapp_prefs", Context.MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun provideUserRepository(api: UserApiService): UserRepository {
        return UserRepository(api)
    }
}