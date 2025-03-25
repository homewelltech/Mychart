package com.chatapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chatapp.data.local.dao.UserDao
import com.chatapp.data.local.dao.FriendDao
import com.chatapp.data.local.dao.GroupDao
import com.chatapp.data.local.dao.MessageDao
import com.chatapp.data.local.dao.ChatRoomDao
import com.chatapp.data.local.entity.User
import com.chatapp.data.local.entity.Friend
import com.chatapp.data.local.entity.Group
import com.chatapp.data.local.entity.Message
import com.chatapp.data.local.entity.ChatRoom

@Database(
    entities = [User::class, Friend::class, Group::class, Message::class, ChatRoom::class],
    version = 1,
    exportSchema = false
)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun friendDao(): FriendDao
    abstract fun groupDao(): GroupDao
    abstract fun messageDao(): MessageDao
    abstract fun chatRoomDao(): ChatRoomDao
}
