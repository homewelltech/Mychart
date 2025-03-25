package com.example.chatapp.di

import android.content.Context
import androidx.room.Room
import com.chatapp.data.local.ChatDatabase
import com.chatapp.data.local.dao.ChatRoomDao
import com.chatapp.data.local.dao.FriendDao
import com.chatapp.data.local.dao.GroupDao
import com.chatapp.data.local.dao.MessageDao
import com.chatapp.data.local.dao.UserDao
import com.chatapp.data.remote.api.ChatRoomApi
import com.chatapp.data.remote.api.FriendApi
import com.chatapp.data.remote.api.GroupApi
import com.chatapp.data.remote.api.MessageApi
import com.chatapp.data.remote.api.UserApi
import com.chatapp.data.remote.interceptor.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val BASE_URL = "http://192.168.10.119:8085" // 替换为实际的 API 地址

    // 提供 Room 数据库实例
    @Provides
    @Singleton
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // 提供各个 DAO
    @Provides
    fun provideUserDao(database: ChatDatabase): UserDao = database.userDao()

    @Provides
    fun provideFriendDao(database: ChatDatabase): FriendDao = database.friendDao()

    @Provides
    fun provideGroupDao(database: ChatDatabase): GroupDao = database.groupDao()

    @Provides
    fun provideMessageDao(database: ChatDatabase): MessageDao = database.messageDao()

    @Provides
    fun provideChatRoomDao(database: ChatDatabase): ChatRoomDao = database.chatRoomDao()

    // 提供 OkHttpClient，并添加日志拦截器
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    // 提供 Retrofit 实例，使用 GsonConverterFactory
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // 替换为实际服务端地址
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 提供 API 接口实例
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFriendApi(retrofit: Retrofit): FriendApi {
        return retrofit.create(FriendApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupApi(retrofit: Retrofit): GroupApi {
        return retrofit.create(GroupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageApi(retrofit: Retrofit): MessageApi {
        return retrofit.create(MessageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRoomApi(retrofit: Retrofit): ChatRoomApi {
        return retrofit.create(ChatRoomApi::class.java)
    }
}
